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

class AdicionarProdutoActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextDescricao: EditText
    private lateinit var editTextPreco: EditText
    private lateinit var editTextImagemUrl: EditText
    private lateinit var buttonSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_produto)

        editTextNome = findViewById(R.id.editTextNome)
        editTextDescricao = findViewById(R.id.editTextDescricao)
        editTextPreco = findViewById(R.id.editTextPreco)
        editTextImagemUrl = findViewById(R.id.editTextImagemUrl)
        buttonSalvar = findViewById(R.id.buttonSalvar)

        buttonSalvar.setOnClickListener {
            // Lógica para salvar o produto
            val nome = editTextNome.text.toString()
            val descricao = editTextDescricao.text.toString()
            val preco = editTextPreco.text.toString().toDouble()
            val imagemUrl = editTextImagemUrl.text.toString()

            val novoProduto = Produto("new_id", nome, descricao, preco, imagemUrl)
            saveProduto(novoProduto) // Método fictício para salvar o produto
            finish() // Voltar para a lista de produtos
        }
    }

    private fun saveProduto(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitHelper.retrofit
            val produtoAPI = retrofit.create(ProdutoAPI::class.java)

            val response = produtoAPI.criarProduto(produto)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AdicionarProdutoActivity, "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish() // Volta para a lista de produtos
                } else {
                    Toast.makeText(this@AdicionarProdutoActivity, "Falha ao adicionar produto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
