package com.example.projet.presentation.api

import retrofit2.http.GET
import retrofit2.http.Path


interface HockeyApi {
    @GET( "hockey")
    fun getHockeyList(): retrofit2.Call<HockeyListResponse>

    @GET( "hockey/{id}")
    fun getHockeyDetail(@Path("id") id: Int): retrofit2.Call<HockeyDetailResponse>
}
