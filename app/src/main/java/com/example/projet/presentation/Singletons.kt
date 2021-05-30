package com.example.projet.presentation

import com.example.projet.presentation.api.HockeyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Singletons {
    companion object{
        val HockeyApi: HockeyApi =  Retrofit.Builder()
            .baseUrl( baseUrl: "https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)

    }
}
