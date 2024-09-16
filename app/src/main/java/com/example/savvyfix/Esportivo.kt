package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Esportivo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_esportivo)

        val iconHomeEsportivo: ImageView = findViewById(R.id.iconHomeOutros)
        val iconCarrinhoEsportivo : ImageView = findViewById(R.id.iconCarrinhoOutros)
        val iconLoginEsportivo : ImageView = findViewById(R.id.iconLoginOutros)
        val sobreSavvyEsportivo : TextView = findViewById(R.id.sobreSavvyOutros)
        val viewSobreSavvyEsportivo : View = findViewById(R.id.viewSobreSavvyOutros)
        val textHomeEsportivo : TextView = findViewById(R.id.textHomeOutros)
        val viewHomeEsportivo : View = findViewById(R.id.viewHomeOutros)

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