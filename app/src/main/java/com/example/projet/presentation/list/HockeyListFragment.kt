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
import com.example.projet.presentation.api.HockeyApi
import com.example.projet.presentation.api.HockeyResponse
import javax.security.auth.callback.Callback


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HockeyListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = HockeyAdapter(listOf(), ::onClickedPokemon)


    private val layoutManager = LinearLayoutManager(context)

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
            layoutManager = this@HockeyListFragment.layoutManager
            adapter = this@HockeyListFragment.adapter
        }

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl( baseUrl: "https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val HockeyApi: HockeyApi = retrofit.create(HockeyApi::class.java)

        HockeyApi.getHockeyList().enqueue(object : Callback<HockeyResponse>{
            override fun onFailure(call: Call<HockeyResponse>, t: Throwable){

        }
            override fun onResponse(call: Call<HockeyResponse>, response: Response<HockeyResponse>){
                if (response.isSuccessful && response.body() != null){
                    val HockeyResponse: HockeyResponse? = response.body()!!
                    adapter.updateList(HockeyResponse.results)
                }
            }

        })

    }
    private fun onClickedPokemon(hockey: Hockey) {
        findNavController().navigate(R.id.navigationToHockeyDetailFragment)
    }
}