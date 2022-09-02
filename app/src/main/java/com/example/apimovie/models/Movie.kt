package com.example.apimovie.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Movie_Table")
data class Movie (
    @PrimaryKey
    val id :Int =-1,
    var page:Int=0,
    @SerializedName("poster_path")
    val poster: String,
    val isAdult:Boolean,
    val overview: String,
    val release_date: String,
    val original_title: String,
    @SerializedName("original_language")
    val language: String ,
    val title: String,
    var backdrop_path: String,
    val popularity :Double,
    val vote_count :Int,
    val vote_average :Double,
    val video :Boolean
)
