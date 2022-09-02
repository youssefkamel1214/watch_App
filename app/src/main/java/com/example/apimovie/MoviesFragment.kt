package com.example.apimovie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.apimovie.controller.MoiveAdapter
import com.example.apimovie.databinding.FragmentMoviesBinding
import com.example.apimovie.viewmodels.MovieViewModelFactory
import com.example.apimovie.viewmodels.MoviesViewModel


class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var biding:FragmentMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        biding=FragmentMoviesBinding.inflate(layoutInflater,container,false)
        viewModel= ViewModelProvider(this, MovieViewModelFactory(requireActivity().application))[MoviesViewModel::class.java]
        biding.viewmodle=viewModel
        biding.lifecycleOwner = this
        val adapter=MoiveAdapter(MoiveAdapter.MovieListhner {
            Toast.makeText(requireContext(),"fff",Toast.LENGTH_SHORT).show()
        })
        biding.rcycler.adapter=adapter
        biding.rcycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                        viewModel.changepagenumber()

                }
            }
        })
        return biding.root
    }



}