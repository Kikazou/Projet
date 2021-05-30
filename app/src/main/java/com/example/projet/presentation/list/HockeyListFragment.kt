package com.example.projet.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R
import com.example.projet.presentation.Singletons
import com.example.projet.presentation.api.HockeyApi
import com.example.projet.presentation.api.HockeyListResponse
import javax.security.auth.callback.Callback


class HockeyListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = HockeyAdapter(listOf(), ::onClickedPokemon)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_hockey_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.hockey_recyclerview)


        recyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = this@HockeyListFragment.adapter
        }


        Singletons.HockeyApi.getHockeyList().enqueue(object : Callback<HockeyListResponse>{
            override fun onFailure(call: Call<HockeyListResponse>, t: Throwable){

        }
            override fun onResponse(call: Call<HockeyListResponse>, response: Response<HockeyListResponse>){
                if (response.isSuccessful && response.body() != null){
                    val HockeyListResponse: HockeyListResponse? = response.body()!!
                    adapter.updateList(HockeyListResponse.results)
                }
            }

        })

    }
    private fun onClickedPokemon(hockey: Hockey) {
        findNavController().navigate(R.id.navigationToHockeyDetailFragment)
    }
}