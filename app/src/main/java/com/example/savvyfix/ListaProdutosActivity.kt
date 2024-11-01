import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savvyfix.AdicionarProdutoActivity
import com.example.savvyfix.EditarProdutoActivity
import com.example.savvyfix.Produto
import com.example.savvyfix.ProdutoAdapter
import com.example.savvyfix.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProdutoAdapter
    private var produtos: MutableList<Produto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)

        firestore = FirebaseFirestore.getInstance()

        recyclerView = findViewById(R.id.recyclerViewProdutos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab: FloatingActionButton = findViewById(R.id.fabAdicionarProduto)
        fab.setOnClickListener {
            val intent = Intent(this, AdicionarProdutoActivity::class.java)
            startActivity(intent)
        }

        loadProdutos()

        adapter = ProdutoAdapter(produtos, { produto -> onProdutoClick(produto) }, { produto -> onProdutoDelete(produto) })
        recyclerView.adapter = adapter
    }

    private fun loadProdutos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val produtosDaApi = loadProdutosFromApi()
                withContext(Dispatchers.Main) {
                    produtos.clear()
                    produtos.addAll(produtosDaApi)
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ListaProdutosActivity, "Erro ao carregar produtos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private suspend fun loadProdutosFromApi(): List<Produto> {
        val produtosList = mutableListOf<Produto>()
        val snapshot = firestore.collection("produtos").get().await() // Usando a extensão do Kotlin Coroutines

            for (document in snapshot.documents) {
            val produto = document.toObject(Produto::class.java)
            produto?.let { produtosList.add(it.copy(id = document.id)) } // Adiciona o ID do documento
        }
        return produtosList
    }

    private fun onProdutoClick(produto: Produto) {
        val intent = Intent(this, EditarProdutoActivity::class.java)
        intent.putExtra("produtoId", produto.id)
        startActivity(intent)
    }

    private fun onProdutoDelete(produto: Produto) {
        // Confirmar exclusão
        AlertDialog.Builder(this)
            .setTitle("Confirmar Exclusão")
            .setMessage("Você realmente deseja excluir este produto?")
            .setPositiveButton("Sim") { _, _ -> deleteProdutoFromApi(produto) }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun deleteProdutoFromApi(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Tente deletar o produto
                firestore.collection("produtos").document(produto.id).delete().await()
                // Se a exclusão for bem-sucedida, remova o produto da lista
                produtos.remove(produto)

                // Volte para o Dispatchers.Main para atualizar a UI
                withContext(Dispatchers.Main) {
                    Snackbar.make(findViewById(android.R.id.content), "Produto adicionado com sucesso!", Snackbar.LENGTH_SHORT).show()
                    finish() // Volta para a lista de produtos
                }

            } catch (e: Exception) {
                // Se falhar, mostre uma mensagem de erro
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ListaProdutosActivity, "Falha ao excluir produto: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
