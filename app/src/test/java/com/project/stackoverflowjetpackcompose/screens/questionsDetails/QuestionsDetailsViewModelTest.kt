package com.project.stackoverflowjetpackcompose.screens.questionsDetails

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.project.stackoverflowjetpackcompose.apiService.FakeItem
import com.project.stackoverflowjetpackcompose.model.Answers.Answers
import com.project.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.project.stackoverflowjetpackcompose.repository.FakeStackOverflowRepositoryError
import com.project.stackoverflowjetpackcompose.repository.FakeStackOverflowRepositorySuccess
import com.project.stackoverflowjetpackcompose.repository.StackOverflowRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock



class QuestionsDetailsViewModelTest {

    private lateinit var viewModel: QuestionsDetailsViewModel
        private val repository: StackOverflowRepository = FakeStackOverflowRepositorySuccess()

    private val repo:StackOverflowRepository = mock{
        onBlocking{getAnswersById(anyInt())} doReturn Answers(true, emptyList(),1500,1000)

    }

    val dispatcher = TestCoroutineDispatcher()
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Initial viewState of questionsViewState is None`() = runTest() {
        viewModel = QuestionsDetailsViewModel(repository)
        val questionViewState = viewModel.questionsViewState
        assertThat(questionViewState, instanceOf(QuestionDetailsViewState.None::class.java))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun ` getQuestionsById should update state to Success when data is fetched successfully `() = runTest()
    {
        val questionId = 1
        val mockQuestions = FakeItem.questionItems
        viewModel = QuestionsDetailsViewModel(repository)
        viewModel.getQuestionsById(questionId)
        assert(viewModel.questionsViewState is QuestionDetailsViewState.Success)
        val successState = viewModel.questionsViewState as QuestionDetailsViewState.Success
        assert(successState.question == mockQuestions)
    }

    @Test
    fun `getQuestionsById should update ViewState to Error when an exception occurs`() {
        val questionId = 1
        val errorMessage = "unknown error occurred"
         val repository:StackOverflowRepository = FakeStackOverflowRepositoryError()
        viewModel = QuestionsDetailsViewModel(repository)
        viewModel.getQuestionsById(questionId)
        assert(viewModel.questionsViewState is QuestionDetailsViewState.Error)
        val errorState = viewModel.questionsViewState as QuestionDetailsViewState.Error
        assert(errorState.message == errorMessage)
    }

    @Test
    fun `getQuestionsById should set ViewState to Loading`() = runBlocking  {

        val questionId = 1
        val delayedRepository = mock(StackOverflowRepository::class.java)
        viewModel = QuestionsDetailsViewModel(delayedRepository)
        val mockQuestions = QuestionItem(false, emptyList(),10,100)

        // Simulate a loading behavior using a mocked repository

            `when`(delayedRepository.getQuestionsById(questionId)).thenAnswer {
                // Simulate a delay - so that the loading can be displayed (waiting for the response)
                Thread.sleep(1000)
                mockQuestions
            }

        viewModel.getQuestionsById(questionId)
        assert(viewModel.questionsViewState is QuestionDetailsViewState.Loading)
    }


    @Test
    fun `InitialState of answersViewState is None`() = runTest() {
        viewModel = QuestionsDetailsViewModel(repo)
        val answerViewState = viewModel.answersViewState
        assertThat(answerViewState, instanceOf(AnswerDetailsViewState.None::class.java))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun ` getAnswersById should update state to Success when data is fetched successfully `() = runTest()
    {
        val questionId = 1
        val mockAnswers = Answers(true, emptyList(),1500,1000)
        viewModel = QuestionsDetailsViewModel(repo)
        viewModel.getAnswersById(questionId)
        assert(viewModel.answersViewState is AnswerDetailsViewState.Success)
        val successState = viewModel.answersViewState as AnswerDetailsViewState.Success
        assert(successState.answer == mockAnswers)
    }

    @Test
    fun `getAnswersById should update ViewState to Error when an exception occurs`() {
        val questionId = 1
        val errorMessage = "unknown error occurred"
        val repository:StackOverflowRepository = FakeStackOverflowRepositoryError()
        viewModel = QuestionsDetailsViewModel(repository)
        viewModel.getAnswersById(questionId)
        assert(viewModel.answersViewState is AnswerDetailsViewState.Error)
        val errorState = viewModel.answersViewState as AnswerDetailsViewState.Error
        assert(errorState.message == errorMessage)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return viewState Loading when getAnswersById is called`() =  runBlocking  {

        val questionId = 1
        val delayedRepository = mock(StackOverflowRepository::class.java)
        viewModel = QuestionsDetailsViewModel(delayedRepository)
        val mockAnswers = Answers(true, emptyList(),1500,1000)

        // Simulate a loading behavior using a mocked repository

        `when`(delayedRepository.getAnswersById(questionId)).thenAnswer {
            // Simulate a delay - so that the loading can be displayed (waiting for the response)
            Thread.sleep(1000)
            mockAnswers
        }

        viewModel.getAnswersById(questionId)
        assert(viewModel.answersViewState is AnswerDetailsViewState.Loading)
    }

}







