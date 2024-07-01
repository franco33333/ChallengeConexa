package com.example.challengeconexa.data.remote

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "https://jsonplaceholder.org/"

    var service = createService()

    private fun createService(): ApiInterface {
        val httpClient = getOkHttpClient()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val sRestAdapter = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()

        return sRestAdapter.create(ApiInterface::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(HttpLoggingInterceptor { message ->
            Log.d("ApiClient", message)
        }.apply { level = HttpLoggingInterceptor.Level.BODY })

        val timeout = 30L
        return builder
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }
}