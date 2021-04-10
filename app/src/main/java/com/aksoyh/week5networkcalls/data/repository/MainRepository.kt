package com.aksoyh.week5networkcalls.data.repository

import com.aksoyh.week5networkcalls.data.api.ApiHelper
import com.aksoyh.week5networkcalls.data.api.ApiService

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}