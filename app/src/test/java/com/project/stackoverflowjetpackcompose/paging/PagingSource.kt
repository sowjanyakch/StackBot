package com.project.stackoverflowjetpackcompose.paging

import androidx.paging.PagingSource
import com.project.stackoverflowjetpackcompose.apiService.FakeItem
import com.project.stackoverflowjetpackcompose.model.Questions.Item
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import com.project.stackoverflowjetpackcompose.network.StackOverflowAPI as StackOverflowAPI1




class PagingSourceTest {


    val items:List<Item> = listOf(FakeItem.item1, FakeItem.item2, FakeItem.item3)

    @Test
    fun `when users are given then users paging source returns success load result`() =
        runBlocking {


            // given

            val questionItem = FakeItem.questionitem
             lateinit var PaginationSource: StackSource

          //  val usersPagingSource = StackSource(StackOverflowAPI1, "votes","android")



            val params = PagingSource
                .LoadParams
                .Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Page(
                    data = items,
                    prevKey = null,
                    nextKey = 20
                )

            // when
            val actual = PaginationSource.load(params = params)

            // then
            assertEquals(expected, actual)
        }


    @Test
    fun `when users are given then users paging source returns error load result`() =
        runBlocking {


        }



}
