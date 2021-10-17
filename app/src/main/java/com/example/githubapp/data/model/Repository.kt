package com.example.githubapp.data.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name")
    val name: String,

    @SerializedName("private")
    val isPrivate: Boolean,

    @SerializedName("language")
    val language: String
)