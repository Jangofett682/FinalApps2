package com.example.myapplication

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import android.media.Image
import android.media.RouteListingPreference.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val playlist: List<Data>) : RecyclerView.Adapter<Adapter.ViewHolder>()
{
     var onItemClickListener: ((Data) -> Unit)? = null

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
    {
        private val textViewTitle : TextView = view.findViewById(R.id.textViewTitle)
        private val textViewArtist : TextView = view.findViewById(R.id.textViewArtist)
        private val textViewAlbum : TextView = view.findViewById(R.id.textViewAlbum)
        fun bind(data: Data)
        {
            textViewTitle.text = data.title
            textViewArtist.text = data.artist.name
            textViewAlbum.text = data.album.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.listitem, parent, false))
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val item = playlist[position]
        holder.bind(item)

        holder.itemView.setOnClickListener{
            onItemClickListener?.invoke(item)
        }
    }


    override fun getItemCount(): Int {
        return playlist.size
    }

}