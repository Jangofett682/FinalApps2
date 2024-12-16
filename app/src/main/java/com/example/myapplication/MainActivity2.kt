package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {

    lateinit var TxVwCancion : TextView
    lateinit var TxVwArtista : TextView
    lateinit var TxVwAlbum : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        TxVwCancion = findViewById(R.id.Cancion)
        TxVwArtista = findViewById(R.id.Artista)
        TxVwAlbum = findViewById(R.id.Album)


        val bundle = intent.extras


        val can = bundle?.getString("title")
        val art = bundle?.getString("artist")
        val alb = bundle?.getString("album")
        TxVwCancion.text = can
        TxVwArtista.text = art
        TxVwAlbum.text = alb
    }
}