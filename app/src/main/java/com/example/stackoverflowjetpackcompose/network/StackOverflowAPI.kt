package com.example.stackoverflowjetpackcompose.network

import com.example.stackoverflowjetpackcompose.model.TopQuestions
import com.example.stackoverflowjetpackcompose.utils.Constants.ITEMS_PER_PAGE
import retrofit2.http.GET
import retrofit2.http.Query


interface StackOverflowAPI
{
    @GET("2.3/questions?order=desc&sort=votes&tagged=android&site=stackoverflow&filter=!nKzQUR3Egv")
    suspend fun getQuestions(@Query("page") page:Int,
                             @Query("pagesize") pagesize:Int = ITEMS_PER_PAGE): TopQuestions
}


