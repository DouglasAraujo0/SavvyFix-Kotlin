package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Sobre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sobre)

        val iconHomeSobre: ImageView = findViewById(R.id.iconHomeSobre)
        val iconCarrinhoSobre : ImageView = findViewById(R.id.iconCarrinhoSobre)
        val iconLoginSobre : ImageView = findViewById(R.id.iconLoginSobre)
        val sobreSavvySobre : TextView = findViewById(R.id.sobreSavvySobre)

        iconHomeSobre.setOnClickListener {
            IrParaHome()
        }
        iconCarrinhoSobre.setOnClickListener {
            IrParaCarrinho()
        }
        iconLoginSobre.setOnClickListener {
            IrParaLogin()
        }
        sobreSavvySobre.setOnClickListener {
            IrParaSobreNos()
        }
    }
    private fun IrParaHome(){
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
    }
    private fun IrParaCarrinho(){
        val carrinho = Intent(this, Carrinho::class.java)
        startActivity(carrinho)
    }
    private fun IrParaLogin(){
        val login = Intent(this, Login::class.java)
        startActivity(login)
    }
    private fun IrParaSobreNos(){
        val sobrenos = Intent(this, Sobre::class.java)
        startActivity(sobrenos)
    }
}