package com.example.trabalho_taep

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var txtLogin = findViewById<EditText>(R.id.txtLoginLogin)
        var txtSenha = findViewById<EditText>(R.id.txtSenhaLogin)
        var btCadastro = findViewById<Button>(R.id.bttCadastro)
        var btLogin = findViewById<Button>(R.id.bttEntrar)

        btCadastro.setOnClickListener{
            val intent = Intent (this, TelaCadastro::class.java)
            startActivity(intent)
        }
    }
}