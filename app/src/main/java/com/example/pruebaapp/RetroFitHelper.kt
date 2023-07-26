package com.example.pruebaapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetroFitHelper {

    private const val URL = "https://superheroapi.com/"

    private val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getInstance():ApiServices{
        return retrofit.create(ApiServices::class.java)
    }
}