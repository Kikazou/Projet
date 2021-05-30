package com.example.projet.presentation.detail

import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.projet.R
import com.example.projet.presentation.Singletons
import com.example.projet.presentation.api.HockeyDetailResponse
import javax.security.auth.callback.Callback

class HockeyDetailFragment : Fragment() {


        private lateinit var textViewName: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_hockey_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.hockey_detail_name)
        callApi()
    }

    private fun callApi(){
        Singletons.HockeyApi.getHockeyDetail("1").enqueue(object : Callback<HockeyDetailResponse>{
            override fun onFailure(
                call: Call<HockeyDetailResponse>,
                t: Throwable
            ){

            }

            override fun onResponse(
                call: Call<HockeyDetailResponse>,
                response: Response<HockeyDetailResponse>

            ){
                if (response.isSuccessful && response.body() != null){
                    textViewName.text = response.body()!!.name
                }

            }
        })
    }
}