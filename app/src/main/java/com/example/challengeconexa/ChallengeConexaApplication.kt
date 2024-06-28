package com.example.challengeconexa

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class ChallengeConexaApplication: Application() {
    companion object {
        lateinit var instance: ChallengeConexaApplication
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        instance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}