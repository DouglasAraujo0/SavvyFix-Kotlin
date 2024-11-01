package com.example.savvyfix

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AdicionarProdutoActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextDescricao: EditText
    private lateinit var editTextPreco: EditText
    private lateinit var buttonSalvar: Button
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_produto)

        editTextNome = findViewById(R.id.editTextNome)
        editTextDescricao = findViewById(R.id.editTextDescricao)
        editTextPreco = findViewById(R.id.editTextPreco)
        buttonSalvar = findViewById(R.id.buttonSalvar)

        firestore = FirebaseFirestore.getInstance() // Inicializa o Firestore

        buttonSalvar.setOnClickListener {
            // Lógica para salvar o produto
            val nome = editTextNome.text.toString()
            val descricao = editTextDescricao.text.toString()
            val precoStr = editTextPreco.text.toString()

            // Verifique se todos os campos obrigatórios estão preenchidos
            if (nome.isBlank() || descricao.isBlank() || precoStr.isBlank()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tente converter o preço para Double
            val preco = try {
                precoStr.toDouble()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Preço inválido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crie um novo produto
            val novoProduto = Produto(id = "", nome = nome, descricao = descricao, preco = preco)
            saveProduto(novoProduto) // Método para salvar o produto no Firestore
        }
    }

    private fun saveProduto(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Adiciona o novo produto à coleção "produtos" no Firestore
                val produtoRef = firestore.collection("produtos").add(produto).await()

                // Armazena o ID do produto, se necessário
                val produtoComId = produto.copy(id = produtoRef.id) // Atualiza o produto com o ID gerado

                withContext(Dispatchers.Main) {
                    Snackbar.make(findViewById(android.R.id.content), "Produto adicionado com sucesso!", Snackbar.LENGTH_SHORT).show()
                    finish() // Volta para a lista de produtos
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AdicionarProdutoActivity, "Erro ao adicionar produto: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
