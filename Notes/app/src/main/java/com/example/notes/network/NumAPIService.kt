package com.example.notes.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://numbersapi.com/"

private const val URL = "http://numbersapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NumAPIService {
    @GET("random/date?json")
    fun getProperties():
            Call<NumProperty>
}

object NumAPI {
    val retrofitService : NumAPIService by lazy {
        retrofit.create(NumAPIService::class.java)
    }
}



//
interface APIService {
    @GET("random/date?json")
    fun get(): Deferred<NumProperty>
}

object Network {
    private val retrofit2 = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val devbytes = retrofit2.create(APIService::class.java)
}
