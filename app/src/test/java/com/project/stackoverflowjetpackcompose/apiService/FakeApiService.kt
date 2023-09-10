package com.project.stackoverflowjetpackcompose.apiService

import com.project.stackoverflowjetpackcompose.model.Answers.Answers
import com.project.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.project.stackoverflowjetpackcompose.model.Questions.Questions
import com.project.stackoverflowjetpackcompose.model.Search.Search
import com.project.stackoverflowjetpackcompose.model.Tags.Tag
import com.project.stackoverflowjetpackcompose.network.StackOverflowAPI

class FakeApiService(): StackOverflowAPI {
    override suspend fun getQuestions(
        page: Int,
        pagesize: Int,
        sort: String,
        tagged: String
    ): Questions {
        return FakeItem.questionitem
    }

    override suspend fun getQuestionsbyid(questionId: Int): QuestionItem {
        TODO("Not yet implemented")
    }

    override suspend fun getAnswersbyid(questionId: Int): Answers {
        TODO("Not yet implemented")
    }

    override suspend fun popularTags(): Tag {
        TODO("Not yet implemented")
    }

    override suspend fun searchQuestion(
        page: Int,
        pagesize: Int,
        sort: String,
        intitle: String
    ): Search {
        TODO("Not yet implemented")
    }
}
