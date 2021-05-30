package com.example.projet.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class HockeyApi {
    @GET( value: "hockey")
    fun getHockeyList() Call<HockeyResponse>
}