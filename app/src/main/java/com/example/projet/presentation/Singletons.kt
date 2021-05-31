package com.example.projet.presentation

import com.example.projet.presentation.HockeyApplication.Companion.context
import com.example.projet.presentation.api.HockeyApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File



class Singletons {

    companion object{
        var cache = Cache(File(context?.cacheDir, "responses"), 10 * 1024 * 1024)

        val okhttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()


        val HockeyApi: HockeyApi =  Retrofit.Builder()
            .baseUrl( "https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()
            .create(com.example.projet.presentation.api.HockeyApi::class.java)


    }
}
