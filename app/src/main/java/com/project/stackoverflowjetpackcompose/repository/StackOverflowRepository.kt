package com.project.stackoverflowjetpackcompose.repository

import androidx.paging.PagingData
import com.project.stackoverflowjetpackcompose.model.Answers.Answers
import com.project.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.project.stackoverflowjetpackcompose.model.Questions.Item
import com.project.stackoverflowjetpackcompose.model.Tags.Tag
import kotlinx.coroutines.flow.Flow

interface StackOverflowRepository {

    fun getQuestions(sort: String, tagged: String): Flow<PagingData<Item>>

   suspend fun getQuestionsById(questionId: Int): QuestionItem

    suspend fun getAnswersById(questionId: Int): Answers

    suspend fun popularTags(): Tag

    fun searchQuestions(
        sort: String,
        intitle: String
    ): Flow<PagingData<com.project.stackoverflowjetpackcompose.model.Search.Item>>

}