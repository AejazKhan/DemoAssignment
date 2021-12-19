package com.example.demo.dummylist.repository

import com.example.demo.api.service.APIService
import com.example.demo.dummylist.model.RandomUsers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by aejaz.khan.
 * Repository for random user API call.
 */
class UserApiRepository @Inject constructor(val retrofit: Retrofit) {

    suspend fun fetchRandomUsers(result:Int) : RandomUsers?{
        val apiService = retrofit.create(APIService::class.java)
        return apiService.getUsersList(result)
    }
}