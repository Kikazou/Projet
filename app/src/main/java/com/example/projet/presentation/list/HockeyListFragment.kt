package com.example.projet.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R


class HockeyListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView

    private val adapter = HockeyAdapter(listOf(), ::onClickedPokemon)

    private val viewModel: HockeyListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_hockey_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.hockey_recyclerview)
        loader = view.findViewById(R.id.hockey_loader)
        textViewError = view.findViewById(R.id.hockey_error)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HockeyListFragment.adapter
        }


        viewModel.hockeyList.observe(viewLifecycleOwner, Observer { hockeyModel ->
            loader.isVisible = hockeyModel is HockeyLoader
            textViewError.isVisible = hockeyModel is HockeyError

            if (hockeyModel is HockeySuccess) {
                adapter.updateList(hockeyModel.hockeyList)
            }
        })
    }


    private fun onClickedPokemon(id: Int) {
        findNavController().navigate(R.id.navigationToHockeyDetailFragment, bundleOf(
            "hockeyId" to (id + 1)
        ))
    }
}