package com.example.stackoverflowjetpackcompose.screens.questionsDetails

import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.example.stackoverflowjetpackcompose.repository.Repository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt


internal class QuestionsDetailsViewModelTest {

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val repository: Repository = mock {
        onBlocking { getQuestionsById(anyInt()) } doReturn QuestionItem(false, emptyList(), 10, 100)
    }
    val viewModel: QuestionsDetailsViewModel by lazy(LazyThreadSafetyMode.NONE) {
        QuestionsDetailsViewModel(repository)
    }

    @Test
    fun `Should have no interactions because viewState is None`() = runTest() {

        val questionViewState = QuestionDetailsViewState.None
        assertThat(questionViewState, instanceOf(QuestionDetailsViewState.None::class.java))

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Should return viewState when getQuestionsById is called `() = runTest()
    {

        val response = viewModel.getQuestionsById(5)
        val questionViewState = QuestionDetailsViewState.Loading
        assertThat(questionViewState, instanceOf(QuestionDetailsViewState.Loading::class.java))

    }

    @Test
    fun `should have no more interactions because answerViewState is None`() = runTest() {
        val answerViewState = AnswerDetailsViewState.None
        assertThat(answerViewState, instanceOf(AnswerDetailsViewState.None::class.java))
    }

    @Test
    fun `should return viewState when getAnswersById is called`() = runTest() {

        val response = viewModel.getAnswersById(5)
        val answerViewState = AnswerDetailsViewState.Loading
        assertThat(answerViewState, instanceOf(AnswerDetailsViewState.Loading::class.java))
    }
}
    








