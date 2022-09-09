package com.example.integradorandroidbored.Model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Companion class use to get the api connection through base URL
 * @author Aponte, Pineda & Tolaba
 */
class Connection {
    companion object {
        fun getApiConnection(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://www.boredapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}