package com.example.myapplication


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ServiceApi
{
    @GET
    suspend fun getPlaylist(@Url url:String): Response<Playlist>
}