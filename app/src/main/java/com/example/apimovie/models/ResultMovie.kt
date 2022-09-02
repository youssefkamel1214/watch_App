package com.example.apimovie.models

import com.google.gson.annotations.SerializedName


data class ResultMovie (
    val page :Int,
    val total_results :Int,
    val total_pages :Int,
    @SerializedName("results")
    var movies: List<Movie>? = null
)
