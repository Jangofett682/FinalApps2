package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    private lateinit var RcVw: RecyclerView
    private lateinit var TxVwTitle : TextView
    private lateinit var TxVwCount : TextView
    private var playlist = mutableListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TxVwTitle = findViewById(R.id.TxVwTitle)
        TxVwCount = findViewById(R.id.TxVwCount)
        RcVw = findViewById(R.id.RcVw)

        RcVw.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(playlist)
        RcVw.adapter = adapter


        getPlaylist()

        adapter.onItemClickListener = { Data  ->
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("title", Data.title)
            intent.putExtra("artist", Data.artist.name )
            intent.putExtra("album", Data.album.title)
            startActivity(intent)
        }
    }

    private fun getPlaylist()
    {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ServiceApi::class.java).getPlaylist("13315425243")
            val response = call.body()

            runOnUiThread{
                if (call.isSuccessful)
                {
                    val Pl = response?.tracks?.data
                    Pl?.forEach{
                        playlist.add(it)
                    }
                    val tracks = response?.tracks
                    val title = response?.title
                    val count = response?.nb_tracks

                    TxVwTitle.text = title
                    TxVwCount.text = "Songs: $count"
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.deezer.com/playlist/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
//https://api.deezer.com/playlist/13315425243









