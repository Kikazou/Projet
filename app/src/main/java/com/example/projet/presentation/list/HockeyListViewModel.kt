package com.example.projet.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet.presentation.Singletons
import com.example.projet.presentation.api.HockeyListResponse
import retrofit2.Response


class HockeyListViewModel : ViewModel(){

    val hockeyList : MutableLiveData<HockeyModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        hockeyList.value = HockeyLoader

        Singletons.HockeyApi.getHockeyList().enqueue(object: retrofit2.Callback<HockeyListResponse> {
            override fun onFailure(call: retrofit2.Call<HockeyListResponse>, t: Throwable) {
                hockeyList.value = HockeyError
            }

            override fun onResponse(call: retrofit2.Call<HockeyListResponse>, response: Response<HockeyListResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val hockeyResponse: HockeyListResponse? = response.body()!!
                    hockeyList.value = HockeySuccess(hockeyResponse.results)

                }else{
                    hockeyList.value = HockeyError
                }
            }

        })
    }
}