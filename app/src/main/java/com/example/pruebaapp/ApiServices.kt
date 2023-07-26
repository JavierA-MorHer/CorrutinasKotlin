package com.example.pruebaapp

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiServices {

    @GET("/api/apikey/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName:String): Response<SuperHeroDataResponse>
}


data class SuperHeroDataResponse(@SerializedName("response") val response:String)