import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savvyfix.Produto
import com.example.savvyfix.ProdutoAdapter
import com.example.savvyfix.R
import com.example.savvyfix.api.ProdutoAPI
import com.example.savvyfix.api.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProdutoAdapter
    private var produtos: MutableList<Produto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)

        recyclerView = findViewById(R.id.recyclerViewProdutos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadProdutos()

        adapter = ProdutoAdapter(produtos, { produto -> onProdutoClick(produto) }, { produto -> onProdutoDelete(produto) })
        recyclerView.adapter = adapter
    }

    private fun loadProdutos() {
        CoroutineScope(Dispatchers.IO).launch {
            val produtosDaApi = loadProdutosFromApi()
            withContext(Dispatchers.Main) {
                produtos.clear()
                produtos.addAll(produtosDaApi)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private suspend fun loadProdutosFromApi(): List<Produto> {
        val retrofit = RetrofitHelper.getInstance()
        val produtoAPI = retrofit.create(ProdutoAPI::class.java)

        val response = produtoAPI.listarProdutos() // Chame o método correto da API
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

    private fun onProdutoClick(produto: Produto) {
        val intent = Intent(this, EditarProdutoActivity::class.java)
        intent.putExtra("produtoId", produto.id)
        startActivity(intent)
    }

    private fun onProdutoDelete(produto: Produto) {
        produtos.remove(produto)
        adapter.notifyDataSetChanged()
        deleteProdutoFromApi(produto.id)
    }

    private fun deleteProdutoFromApi(produtoId: String) {
        val retrofit = RetrofitHelper.getInstance()
        val produtoAPI = retrofit.create(ProdutoAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = produtoAPI.deleteProduto(produtoId)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ListaProdutosActivity, "Produto excluído", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ListaProdutosActivity, "Falha ao excluir produto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
