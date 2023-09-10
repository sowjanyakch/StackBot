package com.project.stackoverflowjetpackcompose.repository

import androidx.paging.PagingData
import com.project.stackoverflowjetpackcompose.model.Answers.Answers
import com.project.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.project.stackoverflowjetpackcompose.model.Questions.Item
import com.project.stackoverflowjetpackcompose.model.Tags.Tag
import com.project.stackoverflowjetpackcompose.repository.StackOverflowRepository
import kotlinx.coroutines.flow.Flow

class FakeStackOverflowRepositoryError():StackOverflowRepository {
    override fun getQuestions(sort: String, tagged: String): Flow<PagingData<Item>> {
        throw RuntimeException("unknown error occurred")
    }

    override suspend fun getQuestionsById(questionId: Int): QuestionItem {
        throw RuntimeException("unknown error occurred")
    }

    override suspend fun getAnswersById(questionId: Int): Answers {
        throw RuntimeException("unknown error occurred")
    }

    override suspend fun popularTags(): Tag {
        throw RuntimeException("unknown error occurred")
    }

    override fun searchQuestions(
        sort: String,
        intitle: String
    ): Flow<PagingData<com.project.stackoverflowjetpackcompose.model.Search.Item>> {
        throw RuntimeException("unknown error occurred")
    }
}
