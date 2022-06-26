package com.example.stackoverflowjetpackcompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.stackoverflowjetpackcompose.model.Answers.Answers
import com.example.stackoverflowjetpackcompose.model.Item
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.example.stackoverflowjetpackcompose.model.Tags.Tag
import com.example.stackoverflowjetpackcompose.network.StackOverflowAPI
import com.example.stackoverflowjetpackcompose.paging.SearchPagingSource
import com.example.stackoverflowjetpackcompose.paging.StackSource
import com.example.stackoverflowjetpackcompose.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val api: StackOverflowAPI){

    fun getQuestions(tagged:String):Flow<PagingData<Item>>{
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                StackSource(api,tagged)
            }
        ).flow
    }

  suspend fun getQuestionsbyid(questionId:Int): QuestionItem {
        return api.getQuestionsbyid(questionId)
    }

    suspend fun getAnswersbyId(questionId:Int):Answers{
        return api.getAnswersbyid(questionId)
    }

    suspend fun populartags(): Tag {
        return api.popularTags()
    }



    fun searchQuestions(intitle:String):Flow<PagingData<com.example.stackoverflowjetpackcompose.model.Search.Item>>{
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(api,intitle)

            }
        ).flow
    }


}

