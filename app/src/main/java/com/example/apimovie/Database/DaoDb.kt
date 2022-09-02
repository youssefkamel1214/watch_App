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
    @Query("select * from Movie_Table where page=:pagegiven  Order by popularity Desc")
    fun getpopalrmovies( pagegiven:Int): LiveData<List<Movie>>
    @Query("select max(page) from Movie_Table limit 1")
    fun get_max_number_of_pages_exist():Int

}