package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        val btnCadastro: Button = findViewById(R.id.btnCadastro)
        val entreAquiCadastro : TextView = findViewById(R.id.entreAquiCadastro)

        btnCadastro.setOnClickListener {
            IrParaHome()
        }

        entreAquiCadastro.setOnClickListener{
            IrParaLogin()
        }
    }
    private fun IrParaHome(){
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
    }

    private fun IrParaLogin() {
        val login = Intent(this, Login::class.java)
        startActivity(login)
    }
}