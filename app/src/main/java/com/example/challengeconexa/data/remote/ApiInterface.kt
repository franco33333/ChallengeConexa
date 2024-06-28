package com.example.challengeconexa.data.remote

import com.example.challengeconexa.data.objects.Post
import com.example.challengeconexa.data.objects.User
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("users")
    suspend fun getUsers(): List<User>

}