package com.example.myapplication

import android.media.Image
import retrofit2.http.Url

data class Playlist
    (
        val title : String,
        val nb_tracks : Int,
        val tracks : Tracks
    ) {
}

data class Tracks
        (
            val data : List<Data>
        )
        data class Data
            (
                val title : String,
                val artist : Artist,
                val album : Album
            )
            data class Artist
                (
                    val name : String
                )
            data class Album
                (
                   val title : String,
                )