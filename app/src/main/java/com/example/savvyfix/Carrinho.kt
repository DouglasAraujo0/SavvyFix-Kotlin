package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Carrinho : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carrinho)

        val iconHomeCarrinho: ImageView = findViewById(R.id.iconHomeCarrinho)
        val iconCarrinhoCarrinho : ImageView = findViewById(R.id.iconCarrinhoCarrinho)
        val iconLoginCarrinho : ImageView = findViewById(R.id.iconLoginCarrinho)
        val textSobreCarrinho : TextView = findViewById(R.id.textSobreCarrinho)
        val viewSobreCarrinho : View = findViewById(R.id.viewSobreCarrinho)
        val textHomeCarrinho : TextView = findViewById(R.id.textHomeCarrinho)
        val viewHomeCarrinho : View = findViewById(R.id.viewHomeCarrinho)

        iconHomeCarrinho.setOnClickListener {
            IrParaHome()
        }
        iconCarrinhoCarrinho.setOnClickListener {
            IrParaCarrinho()
        }
        iconLoginCarrinho.setOnClickListener {
            IrParaLogin()
        }
        textSobreCarrinho.setOnClickListener {
            IrParaSobreNos()
        }
        viewSobreCarrinho.setOnClickListener {
            IrParaSobreNos()
        }
        textHomeCarrinho.setOnClickListener {
            IrParaHome()
        }
        viewHomeCarrinho.setOnClickListener {
            IrParaHome()
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