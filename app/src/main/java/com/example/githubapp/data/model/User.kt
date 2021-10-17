package com.example.githubapp.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val username: String,
    val name: String,
    val email: String,
    val bio: String,
    val location: String,
    val followers: Int,
    val following: Int,
)