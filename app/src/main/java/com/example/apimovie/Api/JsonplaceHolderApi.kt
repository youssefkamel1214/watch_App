package com.example.apimovie.Api

import com.example.apimovie.models.ResultMovie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface JsonplaceHolderApi {

    @GET("popular")
    fun get_Result(@Query("api_key") key: String?, @Query("page") page: Int): Deferred<ResultMovie>?
}