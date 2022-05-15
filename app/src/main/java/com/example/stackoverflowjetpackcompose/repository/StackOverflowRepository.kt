package com.example.stackoverflowjetpackcompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.stackoverflowjetpackcompose.model.Item
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.example.stackoverflowjetpackcompose.network.StackOverflowAPI
import com.example.stackoverflowjetpackcompose.paging.StackSource
import com.example.stackoverflowjetpackcompose.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val api: StackOverflowAPI){


    fun getQuestions():Flow<PagingData<Item>>{

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                StackSource(api)
            }
        ).flow

    }

  suspend fun getQuestionsbyid(questionId:Int): QuestionItem {
        return api.getQuestionsbyid(questionId)
    }

}

