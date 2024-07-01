package com.example.challengeconexa.data.repository

import com.example.challengeconexa.data.remote.ApiClient
import com.example.challengeconexa.data.remote.ApiInterface

class JsonPlaceholderRepository {
    private var apiService: ApiInterface? = null

    init {
        apiService = ApiClient.service
    }

    suspend fun getPosts() = apiService?.getPosts()

    suspend fun getUsers() = apiService?.getUsers()
}