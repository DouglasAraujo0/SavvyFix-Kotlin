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
        val iconCarrinhoHome: ImageView = findViewById(R.id.iconCarrinhoHome)
        val iconLoginHome: ImageView = findViewById(R.id.iconLoginHome)
        val sobreSavvyHome: TextView = findViewById(R.id.sobreSavvyHome)
        val tenisCasual: ImageView = findViewById(R.id.tenisCasual1)
        val tenisEsportivo: ImageView = findViewById(R.id.tenisEsportivo)
        val tenisOutrosTipos: ImageView = findViewById(R.id.tenisOutrosTipos)

        iconHomeHome.setOnClickListener { irParaActivity(MainActivity::class.java) }
        iconCarrinhoHome.setOnClickListener { irParaActivity(Carrinho::class.java) }
        iconLoginHome.setOnClickListener { irParaActivity(Login::class.java) }
        sobreSavvyHome.setOnClickListener { irParaActivity(Sobre::class.java) }
        tenisCasual.setOnClickListener { irParaActivity(Casual::class.java) }
        tenisEsportivo.setOnClickListener { irParaActivity(Esportivo::class.java) }
        tenisOutrosTipos.setOnClickListener { irParaActivity(OutrosTipos::class.java) }
    }

    private fun irParaActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
