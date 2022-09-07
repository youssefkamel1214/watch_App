package com.example.apimovie.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apimovie.models.Movie

@Dao
interface DaoDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllmovies( list: ArrayList <Movie>)
    @Query("select * from Movie_Table where popularity<:popalritygiver  Order by popularity Desc limit 10")
    fun getpopalrmovies( popalritygiver:Double): LiveData<List<Movie>>
    @Query("select min(popularity) from Movie_Table limit 1")
    fun get_min_popalarity():Double

}