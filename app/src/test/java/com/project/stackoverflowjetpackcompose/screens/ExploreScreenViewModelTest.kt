package com.project.stackoverflowjetpackcompose.screens

import android.util.Log
import com.project.stackoverflowjetpackcompose.apiService.FakeItem
import com.project.stackoverflowjetpackcompose.repository.FakeStackOverflowRepositoryError
import com.project.stackoverflowjetpackcompose.repository.FakeStackOverflowRepositorySuccess
import com.project.stackoverflowjetpackcompose.repository.StackOverflowRepository
import com.project.stackoverflowjetpackcompose.screens.explore.ExploreScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class ExploreScreenViewModelTest {

    private lateinit var viewModel: ExploreScreenViewModel
    private val repository: StackOverflowRepository = FakeStackOverflowRepositorySuccess()
    val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `fetchTags() should fetch the popular tags list from the api`() = runTest()
    {
        val mockTags = FakeItem.tagItems
        viewModel = ExploreScreenViewModel(repository)
        viewModel.fetchTags()
        assert(viewModel.getTags == mockTags.items)
    }


    @Test
    fun `fetchTags() displays a error message in log when an exception occurs`() = runTest() {

        val fakeRepository: StackOverflowRepository = FakeStackOverflowRepositoryError()
        viewModel = ExploreScreenViewModel(fakeRepository)
        viewModel.fetchTags()
        assertThrows(Exception::class.java) {
            viewModel.fetchTags()
            `when`(Log.d("Tag", "Message")).thenAnswer {
                // Your custom behavior here
                println("Error")
            }
        }

    }
}


