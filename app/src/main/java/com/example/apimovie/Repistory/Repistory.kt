package com.example.apimovie.Repistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apimovie.Api.Network
import com.example.apimovie.Constants
import com.example.apimovie.Database.DaoDb
import com.example.apimovie.models.Movie

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
//this class used to take data from api (nasa) than cach it on db
//and also used to clear old data from db
class Repistory (private val database: DaoDb){
    private var page=1
    private var total=1
    private var minpop=Double.MAX_VALUE
    fun valid():Boolean = page<=total
    suspend fun refersh(minpop:Double){
        val retorofit=Network.Service
        try {
            if(minpop>this.minpop)
                return
            val resultmovie=retorofit.get_Result(Constants.base_key,page)!!.await()
            val pagenumber=resultmovie.page
            val listmovie=resultmovie.movies
            listmovie?.forEach {
                it.page=pagenumber
            }
            withContext(Dispatchers.IO){
                database.insertAllmovies(listmovie?.toMutableList() as ArrayList<Movie> )
            }
            page++
            total=resultmovie.total_pages
        }catch (e:Exception){
            Log.d("error",e.message.toString())
        }
        return
    }
}