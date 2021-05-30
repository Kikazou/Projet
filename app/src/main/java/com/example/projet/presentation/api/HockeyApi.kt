package com.example.projet.presentation.api

import android.telecom.Call
import retrofit2.Call
import retrofit2.http.GET


class HockeyApi {
    @GET( value: "hockey")
    fun getHockeyList(): Call<HockeyListResponse>

    @GET( value: "hockey/{id}")
    fun getHockeyDetail(@Path (value: "id") id: String):  Call<HockeyDetailResponse>
}


