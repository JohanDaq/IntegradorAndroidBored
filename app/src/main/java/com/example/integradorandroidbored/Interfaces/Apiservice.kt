package com.example.integradorandroidbored.Interfaces

import com.example.integradorandroidbored.Model.BoredResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface Apiservice {

    @GET("activity")
    suspend fun getBoredResponse(@QueryMap options: Map<String, String>): Response<BoredResponse>
  /*  @GET("?type=")
    suspend fun getActivitiesBType(@Query ("type") q: String): Response<BoredResponse>*/

    @GET("activity")
    suspend fun getActivitiesByParticipants(@Query ("participants") q: String): Response<BoredResponse>

    @GET("activity")
    suspend fun getActivitiesByParticipantsAndType(@Query ("type") type: String,@Query ("participants") participants: String): Response<BoredResponse>
}