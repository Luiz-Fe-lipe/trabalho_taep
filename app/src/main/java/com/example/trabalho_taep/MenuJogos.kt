package com.example.trabalho_taep

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuJogos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_jogos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menuJogos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageButton = findViewById<ImageButton>(R.id.btn_jkp)
        imageButton.setOnClickListener {
            val intent = Intent(this, InicioJokenpo::class.java)
            startActivity(intent)
        }


    }
}