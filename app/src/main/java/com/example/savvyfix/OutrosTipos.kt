package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class OutrosTipos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_outros_tipos)

        val iconHomeOutros: ImageView = findViewById(R.id.iconHomeOutros)
        val iconCarrinhoOutros : ImageView = findViewById(R.id.iconCarrinhoOutros)
        val iconLoginOutros : ImageView = findViewById(R.id.iconLoginOutros)
        val sobreSavvyOutros : TextView = findViewById(R.id.sobreSavvyOutros)
        val viewSobreSavvyOutros : View = findViewById(R.id.viewSobreSavvyOutros)
        val textHomeOutros : TextView = findViewById(R.id.textHomeOutros)
        val viewHomeOutros : View = findViewById(R.id.viewHomeOutros)

        iconHomeOutros.setOnClickListener {
            IrParaHome()
        }
        iconCarrinhoOutros.setOnClickListener {
            IrParaCarrinho()
        }
        iconLoginOutros.setOnClickListener {
            IrParaLogin()
        }
        sobreSavvyOutros.setOnClickListener {
            IrParaSobreNos()
        }
        viewSobreSavvyOutros.setOnClickListener {
            IrParaSobreNos()
        }
        textHomeOutros.setOnClickListener {
            IrParaHome()
        }
        viewHomeOutros.setOnClickListener {
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