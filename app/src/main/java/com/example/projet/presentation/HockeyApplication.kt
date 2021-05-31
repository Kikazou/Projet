package com.example.projet.presentation

import android.app.Application
import android.content.Context

class HockeyApplication : Application() {

    companion object{
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}