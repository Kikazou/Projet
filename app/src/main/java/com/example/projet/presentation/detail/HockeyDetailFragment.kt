package com.example.projet.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.projet.R
import com.example.projet.presentation.Singletons
import com.example.projet.presentation.api.HockeyDetailResponse
import retrofit2.Response
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
        val id = arguments?.getInt("hockeyId") ?: -1
        Singletons.HockeyApi.getHockeyDetail(id).enqueue(object : retrofit2.Callback<HockeyDetailResponse>{
            override fun onFailure(
                call: retrofit2.Call<HockeyDetailResponse>,
                t: Throwable
            ){

            }

            override fun onResponse(
                call: retrofit2.Call<HockeyDetailResponse>,
                response: Response<HockeyDetailResponse>

            ){
                if (response.isSuccessful && response.body() != null){
                    textViewName.text = response.body()!!.name
                }

            }
        })
    }
}