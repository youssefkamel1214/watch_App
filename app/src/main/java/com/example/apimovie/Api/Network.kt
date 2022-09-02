package com.example.apimovie.Api

import com.example.apimovie.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    val Service = retrofit.create(JsonplaceHolderApi::class.java)

}