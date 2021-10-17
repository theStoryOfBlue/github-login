package com.example.githubapp.network

import com.example.githubapp.data.model.AccessToken
import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.model.User
import com.example.githubapp.utils.Constants
import retrofit2.http.*

interface GithubApi {

    companion object{
        const val BASE_URL = Constants.apiURL
    }

    @Headers("Accept: application/json")
    @POST(Constants.domainURL + "login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): AccessToken

    @Headers("Accept: application/json")
    @GET("user/repos")
    suspend fun getRepositories(
    @Header("authorization") token: String
    ): List<Repository>

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun getUserData(
        @Header("authorization") token: String
    ): User

}