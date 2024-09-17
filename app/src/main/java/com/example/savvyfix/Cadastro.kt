package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.savvyfix.api.RetrofitHelper
import com.example.savvyfix.api.ViaCepAPI
import com.example.savvyfix.model.ViaCepResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class Cadastro : AppCompatActivity() {
    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        val btnCadastro: Button = findViewById(R.id.btnCadastro)
        val entreAquiCadastro: TextView = findViewById(R.id.entreAquiCadastro)

        btnCadastro.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                recuperarEndereco()
                runOnUiThread {
                    IrParaLogin()
                }
            }
        }

        entreAquiCadastro.setOnClickListener {
            IrParaLogin()
        }
    }

    private fun IrParaLogin() {
        val login = Intent(this, Login::class.java)
        startActivity(login)
    }

    private suspend fun recuperarEndereco() {
        var retorno: Response<ViaCepResponse>? = null
        val cepDigitadoUsuario = "04727000"

        try {
            val enderecoAPI = retrofit.create(ViaCepAPI::class.java)
            retorno = enderecoAPI.recuperarEndereco(cepDigitadoUsuario)
        } catch (e: Exception) {
            Log.e("Cadastro", "Erro ao recuperar endereço", e)
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val endereco = retorno.body()
                val rua = endereco?.logradouro
                val bairro = endereco?.bairro
                val cidade = endereco?.localidade
                val estado = endereco?.uf
                Log.i("Cadastro", "RUA: $rua")
                Log.i("Cadastro", "BAIRRO: $bairro")
                Log.i("Cadastro", "CIDADE: $cidade")
                Log.i("Cadastro", "ESTADO: $estado")
            } else {
                Log.e("Cadastro", "Erro na resposta da API: ${retorno.code()}")
            }
        } else {
            Log.e("Cadastro", "Resposta da API é nula")
        }
    }
}
