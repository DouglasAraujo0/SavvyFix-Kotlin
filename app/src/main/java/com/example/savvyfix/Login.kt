package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        val cadastreseLogin : TextView = findViewById(R.id.cadastreseLogin)

        btnLogin.setOnClickListener {
            IrParaHome()
        }
        cadastreseLogin.setOnClickListener {
            IrParaCadastro()
        }
    }
    private fun IrParaHome(){
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
    }
    private fun IrParaCadastro(){
        val cadastro = Intent(this, Cadastro::class.java)
        startActivity(cadastro)
    }

}