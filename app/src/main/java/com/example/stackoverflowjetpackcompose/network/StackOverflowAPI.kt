package com.example.stackoverflowjetpackcompose.network

import com.example.stackoverflowjetpackcompose.BuildConfig
import com.example.stackoverflowjetpackcompose.model.TopQuestions
import com.example.stackoverflowjetpackcompose.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface StackOverflowAPIService
{
    @GET("2.3/questions?order=desc&sort=votes&tagged=android&site=stackoverflow&filter=!nKzQUR3Egv")
    suspend fun getQuestions(@Query("page") page:Int,
                             @Query("pagesize") pagesize:Int = 30): TopQuestions
}

private val okHttpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
    }
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(okHttpLoggingInterceptor)
    .build()

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .callFactory(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object StackOverflowAPI{
    val retrofitService: StackOverflowAPIService by lazy {
        retrofit.create(StackOverflowAPIService::class.java)
    }
}
