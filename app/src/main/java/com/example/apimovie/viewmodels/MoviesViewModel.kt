package com.example.apimovie.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.apimovie.Database.RoomDb
import com.example.apimovie.Repistory.Repistory
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val database= RoomDb.getInstance(application).daodb
    val repistory=Repistory(database)
    private val _loading=MutableLiveData<Boolean>(false)
    val loading:LiveData<Boolean> get() = _loading
    private val _firstloading=MutableLiveData<Boolean>(false)
    val firstloading:LiveData<Boolean> get() = _firstloading
    private  var pagenumber=MutableLiveData<Int>(1)
    val movielist=Transformations.map(pagenumber){
            database.getpopalrmovies(it)
    }
    private var totalpages by Delegates.notNull<Int>()

    init {
        viewModelScope.launch {
            _loading.postValue(true)
            _firstloading.postValue(true)
             totalpages =repistory.refersh(1)
            _loading.postValue(false)
            _firstloading.postValue(false)
        }
    }
   fun changepagenumber(){
       if(pagenumber.value!!+1>totalpages||_firstloading.value!!||_loading.value!!)
           return
       viewModelScope.launch {
           _loading.postValue(true)
           repistory.refersh(pagenumber.value!!+1)
           pagenumber.value=pagenumber.value!!+1
           _loading.postValue(false)
       }
   }
}