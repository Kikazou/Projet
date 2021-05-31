package com.example.projet.presentation.list

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet.presentation.Singletons
import com.example.projet.presentation.api.HockeyListResponse
import javax.security.auth.callback.Callback

class HockeyListViewModel : ViewModel(){
    val hockeyList : MutableLiveData<HockeyModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        hockeyList.value = HockeyLoader

        Singletons.HockeyApi.getHockeyList().enqueue(object : Callback<HockeyListResponse> {
            override fun onFailure(call: Call<HockeyListResponse>, t: Throwable) {
                hockeyList.value = HockeyError
            }

            override fun onResponse(call: Call<HockeyListResponse>, response: response<HockeyListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val hockeyResponse: HockeyListResponse? = response.body()!!
                    hockeyList.value = HockeySuccess(hockeyResponse.results)

                }else{
                    hockeyList.value = HockeyError
                }
            }

        })
    }
}