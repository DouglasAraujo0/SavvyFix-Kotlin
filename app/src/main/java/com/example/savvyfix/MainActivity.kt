package com.example.savvyfix

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val iconHomeHome: ImageView = findViewById(R.id.iconHomeHome)
        val iconCarrinhoHome : ImageView = findViewById(R.id.iconCarrinhoHome)
        val iconLoginHome : ImageView = findViewById(R.id.iconLoginHome)
        val sobreSavvyHome: TextView = findViewById(R.id.sobreSavvyHome)
        val tenisCasual: ImageView = findViewById(R.id.tenisCasual1)
        val tenisEsportivo : ImageView = findViewById(R.id.tenisEsportivo)
        val tenisOutrosTipos: ImageView = findViewById(R.id.tenisOutrosTipos)

        iconHomeHome.setOnClickListener {
            IrParaHome()
        }
        iconCarrinhoHome.setOnClickListener {
            IrParaCarrinho()
        }
        iconLoginHome.setOnClickListener {
            IrParaLogin()
        }
        sobreSavvyHome.setOnClickListener {
            IrParaSobreNos()
        }
        tenisCasual.setOnClickListener {
            IrParaCasual()
        }
        tenisEsportivo.setOnClickListener {
            IrParaEsportivo()
        }
        tenisOutrosTipos.setOnClickListener {
            IrParaOutrosTipos()
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
    private fun IrParaCasual(){
        val casual = Intent(this, Casual::class.java)
        startActivity(casual)
    }
    private fun IrParaEsportivo(){
        val esportivo = Intent(this, Esportivo::class.java)
        startActivity(esportivo)
    }
    private fun IrParaOutrosTipos(){
        val outrosTipos = Intent(this, OutrosTipos::class.java)
        startActivity(outrosTipos)
    }
}