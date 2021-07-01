package com.example.samplespacex.data.source.remote

import com.example.samplespacex.data.model.Rocket
import com.example.samplespacex.data.model.RocketDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiHandler {

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/v4/"
    }

    @GET("rockets")
    suspend fun getRockets(): List<Rocket>

    @GET("rockets/{post_id}")
    suspend fun getRocketsDetails(@Path("post_id") deviceId: String,): RocketDetails

}