package com.aksoyh.week5networkcalls.data.api

import com.aksoyh.week5networkcalls.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<MutableList<User>>
}