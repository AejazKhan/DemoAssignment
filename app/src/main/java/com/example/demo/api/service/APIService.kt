package com.example.demo.api.service

import com.example.demo.dummylist.model.RandomUsers
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aejaz.khan.
 */
interface APIService {
    @GET("/api/")
    suspend fun getUsersList(@Query("results") result : Int): RandomUsers?

}