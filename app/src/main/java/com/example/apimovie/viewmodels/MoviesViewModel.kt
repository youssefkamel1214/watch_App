package com.example.apimovie.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.apimovie.Database.RoomDb
import com.example.apimovie.Repistory.Repistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val database= RoomDb.getInstance(application).daodb
    val repistory=Repistory(database)
    private val _loading=MutableLiveData<Boolean>(false)
    val loading:LiveData<Boolean> get() = _loading
    private val _firstloading=MutableLiveData<Boolean>(false)
    val firstloading:LiveData<Boolean> get() = _firstloading
    private  var popalrity=MutableLiveData<Double>(Double.MAX_VALUE)
    val movielist=Transformations.map(popalrity){
            database.getpopalrmovies(it)
    }
    init {
        viewModelScope.launch {
            _loading.postValue(true)
            _firstloading.postValue(true)
            repistory.refersh(popalrity.value!!)
            _loading.postValue(false)
            _firstloading.postValue(false)
        }
    }
   fun changepagenumber(pop:Double){
       if(_firstloading.value!!||_loading.value!!)
           return
       viewModelScope.launch {
           _loading.postValue(true)
           if(repistory.valid())
              repistory.refersh(pop)
           val b= withContext(Dispatchers.IO){
             return@withContext pop> database.get_min_popalarity()
           }
           if(b)
               popalrity.postValue(pop)
           _loading.postValue(false)
       }
   }
}