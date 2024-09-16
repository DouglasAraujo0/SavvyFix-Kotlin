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

class Casual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_casual)

        val iconHomeCasual: ImageView = findViewById(R.id.iconHomeCasual)
        val iconCarrinhoCasual : ImageView = findViewById(R.id.iconCarrinhoCasual)
        val iconLoginCasual : ImageView = findViewById(R.id.iconLoginCasual)
        val sobreSavvyCasual : TextView = findViewById(R.id.sobreSavvyCasual)
        val viewSobreSavvyCasual : View = findViewById(R.id.viewSobreSavvyCasual)
        val textHomeCasual : TextView = findViewById(R.id.textHomeCasual)
        val viewHomeCasual : View = findViewById(R.id.viewHomeCasual)

        iconHomeCasual.setOnClickListener {
            IrParaHome()
        }
        iconCarrinhoCasual.setOnClickListener {
            IrParaCarrinho()
        }
        iconLoginCasual.setOnClickListener {
            IrParaLogin()
        }
        sobreSavvyCasual.setOnClickListener {
            IrParaSobreNos()
        }
        viewSobreSavvyCasual.setOnClickListener {
            IrParaSobreNos()
        }
        textHomeCasual.setOnClickListener {
            IrParaHome()
        }
        viewHomeCasual.setOnClickListener {
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