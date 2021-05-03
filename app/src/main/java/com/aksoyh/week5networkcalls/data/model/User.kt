package com.aksoyh.week5networkcalls.data.model

import java.io.Serializable

data class User(
    val avatar: String,
    val email: String,
    val id: String,
    val name: String
) : Serializable