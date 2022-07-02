package com.example.stackoverflowjetpackcompose.network

import com.example.stackoverflowjetpackcompose.model.Answers.Answers
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.example.stackoverflowjetpackcompose.model.Questions.Questions
import com.example.stackoverflowjetpackcompose.model.Search.Search
import com.example.stackoverflowjetpackcompose.model.Tags.Tag
import com.example.stackoverflowjetpackcompose.utils.Constants.ITEMS_PER_PAGE
import com.example.stackoverflowjetpackcompose.utils.Constants.Key
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StackOverflowAPI
{
    @GET("2.3/questions?order=desc&site=stackoverflow&filter=!T1gn2__R7RJ(mj5xFm")
    suspend fun getQuestions(@Query("page") page:Int,
                             @Query("pagesize") pagesize:Int = ITEMS_PER_PAGE,
                             @Query("sort") sort:String,
                             @Query("tagged") tagged:String,
                             @Query("key") key:String = Key): Questions

    @GET("/2.3/questions/{ids}?order=desc&sort=activity&site=stackoverflow&filter=!.KWpexRLnilEdYD1dnmMsENXUo58*")
    suspend fun getQuestionsbyid(@Path("ids") questionId:Int,
                                  @Query("key") key:String = Key): QuestionItem

    @GET("/2.3/questions/{ids}/answers?order=desc&sort=activity&site=stackoverflow&filter=!*MZqiH2Uew_wi.tT")
    suspend fun getAnswersbyid(@Path("ids") questionId:Int,
                               @Query("key") key:String = Key):Answers

    @GET("/2.3/tags?order=desc&sort=popular&site=stackoverflow")
    suspend fun popularTags(
        @Query("key") key:String = Key
    ): Tag

    @GET("/2.3/search/advanced?order=desc&site=stackoverflow&filter=!6VvPDzPyzuCbL")
    suspend fun searchQuestion(@Query("page") page:Int,
                               @Query("pagesize") pagesize:Int = ITEMS_PER_PAGE,
                               @Query("sort") sort:String,
                               @Query ("q") q:String,
                               @Query("key") key:String = Key): Search

}


