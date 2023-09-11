package com.project.stackoverflowjetpackcompose.repository

import androidx.paging.PagingData
import com.project.stackoverflowjetpackcompose.apiService.FakeItem
import com.project.stackoverflowjetpackcompose.model.Answers.Answers
import com.project.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.project.stackoverflowjetpackcompose.model.Questions.Item
import com.project.stackoverflowjetpackcompose.model.Tags.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeStackOverflowRepositorySuccess():StackOverflowRepository {
    override fun getQuestions(sort: String, tagged: String): Flow<PagingData<Item>> {
        return flow{
            PagingData.from(listOf(FakeItem.item1,FakeItem.item2,FakeItem.item3))
        }
    }

    override suspend fun getQuestionsById(questionId: Int): QuestionItem {
        return FakeItem.questionItems
    }

    override suspend fun getAnswersById(questionId: Int): Answers {
        TODO("Not yet implemented")
    }

    override suspend fun popularTags(): Tag {
       return FakeItem.tagItems
    }

    override fun searchQuestions(
        sort: String,
        intitle: String
    ): Flow<PagingData<com.project.stackoverflowjetpackcompose.model.Search.Item>> {
        TODO("Not yet implemented")
    }
}
