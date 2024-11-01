package com.example.savvyfix

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.savvyfix.api.ProdutoAPI
import com.example.savvyfix.api.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditarProdutoActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextDescricao: EditText
    private lateinit var editTextPreco: EditText
    private lateinit var editTextImagemUrl: EditText
    private lateinit var buttonAtualizar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_produto)

        editTextNome = findViewById(R.id.editTextNome)
        editTextDescricao = findViewById(R.id.editTextDescricao)
        editTextPreco = findViewById(R.id.editTextPreco)
        editTextImagemUrl = findViewById(R.id.editTextImagemUrl)
        buttonAtualizar = findViewById(R.id.buttonAtualizar)

        // Carregar o produto para edição
        val produtoId = intent.getStringExtra("produtoId")
        val produto = loadProduto(produtoId) // Método fictício para carregar o produto
        editTextNome.setText(produto.nome)
        editTextDescricao.setText(produto.descricao)
        editTextPreco.setText(produto.preco.toString())
        editTextImagemUrl.setText(produto.imagemUrl)

        buttonAtualizar.setOnClickListener {
            // Lógica para atualizar o produto
            val nome = editTextNome.text.toString()
            val descricao = editTextDescricao.text.toString()
            val preco = editTextPreco.text.toString().toDouble()
            val imagemUrl = editTextImagemUrl.text.toString()

            val produtoAtualizado = Produto(produtoId!!, nome, descricao, preco, imagemUrl)
            updateProduto(produtoAtualizado) // Método fictício para atualizar o produto
            finish() // Voltar para a lista de produtos
        }
    }

    // Exemplo de método fictício para carregar o produto
    private fun loadProduto(id: String?): Produto {
        // Implementar a lógica de carregamento do produto
        return Produto(id ?: "", "Nome", "Descrição", 0.0, "url_imagem")
    }

    private fun updateProduto(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitHelper.retrofit
            val produtoAPI = retrofit.create(ProdutoAPI::class.java)

            val response = produtoAPI.atualizarProduto(produto.id, produto)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditarProdutoActivity, "Produto atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish() // Volta para a lista de produtos
                } else {
                    Toast.makeText(this@EditarProdutoActivity, "Falha ao atualizar produto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
