package com.project.stackoverflowjetpackcompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.stackoverflowjetpackcompose.model.Questions.Item
import com.project.stackoverflowjetpackcompose.model.Tags.Tag
import com.project.stackoverflowjetpackcompose.model.Answers.Answers
import com.project.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.project.stackoverflowjetpackcompose.network.StackOverflowAPI
import com.project.stackoverflowjetpackcompose.paging.SearchPagingSource
import com.project.stackoverflowjetpackcompose.paging.StackSource
import com.project.stackoverflowjetpackcompose.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val api: StackOverflowAPI) {

    fun getQuestions(sort: String, tagged: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                StackSource(api, sort, tagged)
            }
        ).flow
    }

    suspend fun getQuestionsById(questionId: Int): QuestionItem {
        return api.getQuestionsbyid(questionId)
    }

    suspend fun getAnswersById(questionId: Int): Answers {
        return api.getAnswersbyid(questionId)
    }

    suspend fun popularTags(): Tag {
        return api.popularTags()
    }


    fun searchQuestions(
        sort: String,
        intitle: String
    ): Flow<PagingData<com.project.stackoverflowjetpackcompose.model.Search.Item>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(api, sort, intitle)

            }
        ).flow
    }


}

