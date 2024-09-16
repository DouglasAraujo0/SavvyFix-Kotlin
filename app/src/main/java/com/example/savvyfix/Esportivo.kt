package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Esportivo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_esportivo)

        val iconHomeEsportivo: ImageView = findViewById(R.id.iconHomeEsportivo)
        val iconCarrinhoEsportivo : ImageView = findViewById(R.id.iconCarrinhoEsportivo)
        val iconLoginEsportivo : ImageView = findViewById(R.id.iconLoginEsportivo)
        val sobreSavvyEsportivo : TextView = findViewById(R.id.sobreSavvyEsportivo)
        val viewSobreSavvyEsportivo : View = findViewById(R.id.viewSobreSavvyEsportivo)
        val textHomeEsportivo : TextView = findViewById(R.id.textHomeEsportivo)
        val viewHomeEsportivo : View = findViewById(R.id.viewHomeEsportivo)

        iconHomeEsportivo.setOnClickListener {
            IrParaHome()
        }
        iconCarrinhoEsportivo.setOnClickListener {
            IrParaCarrinho()
        }
        iconLoginEsportivo.setOnClickListener {
            IrParaLogin()
        }
        sobreSavvyEsportivo.setOnClickListener {
            IrParaSobreNos()
        }
        viewSobreSavvyEsportivo.setOnClickListener {
            IrParaSobreNos()
        }
        textHomeEsportivo.setOnClickListener {
            IrParaHome()
        }
        viewHomeEsportivo.setOnClickListener {
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