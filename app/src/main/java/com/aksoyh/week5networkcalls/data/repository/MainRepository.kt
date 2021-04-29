package com.aksoyh.week5networkcalls.data.repository

import com.aksoyh.week5networkcalls.data.api.RetrofitBuilder

class MainRepository {
    suspend fun getUsers() = RetrofitBuilder.api.getUsers()
}