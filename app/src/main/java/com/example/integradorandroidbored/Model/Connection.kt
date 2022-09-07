package com.example.integradorandroidbored.Model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Connection {

    //companion objetc para ppoder utilizar en cualquier sin necesidad de instanciar (clase statica)
    companion object {
        fun getApiConexion(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://www.boredapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}