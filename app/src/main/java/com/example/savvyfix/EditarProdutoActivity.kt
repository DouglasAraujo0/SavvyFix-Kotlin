package com.example.savvyfix

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class EditarProdutoActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextDescricao: EditText
    private lateinit var editTextPreco: EditText
    private lateinit var buttonAtualizar: Button
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_produto)

        editTextNome = findViewById(R.id.editTextNome)
        editTextDescricao = findViewById(R.id.editTextDescricao)
        editTextPreco = findViewById(R.id.editTextPreco)
        buttonAtualizar = findViewById(R.id.buttonAtualizar)

        firestore = FirebaseFirestore.getInstance() // Inicializa o Firestore

        // Carregar o produto para edição
        val produtoId = intent.getStringExtra("produtoId")

        if (produtoId != null) {
            loadProduto(produtoId) // Carregar o produto do Firestore
        } else {
            Toast.makeText(this, "ID do produto não encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        buttonAtualizar.setOnClickListener {
            // Lógica para atualizar o produto
            val nome = editTextNome.text.toString()
            val descricao = editTextDescricao.text.toString()
            val precoStr = editTextPreco.text.toString()

            // Verifique se todos os campos obrigatórios estão preenchidos
            if (nome.isBlank() || descricao.isBlank() || precoStr.isBlank()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val preco = try {
                precoStr.toDouble()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Preço inválido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val produtoAtualizado = Produto(produtoId, nome, descricao, preco)
            updateProduto(produtoAtualizado)
        }

    }

    // Método para carregar o produto do Firestore
    private fun loadProduto(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val document = firestore.collection("produtos").document(id).get().await()
                if (document.exists()) {
                    val produto = document.toObject(Produto::class.java)
                    withContext(Dispatchers.Main) {
                        if (produto != null) {
                            editTextNome.setText(produto.nome)
                            editTextDescricao.setText(produto.descricao)
                            editTextPreco.setText(produto.preco.toString())
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@EditarProdutoActivity, "Produto não encontrado", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@EditarProdutoActivity, "Erro ao carregar produto: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateProduto(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                firestore.collection("produtos").document(produto.id).set(produto).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@EditarProdutoActivity, "Produto atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish() // Volta para a lista de produtos
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@EditarProdutoActivity, "Falha ao atualizar produto: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
