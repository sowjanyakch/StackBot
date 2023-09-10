package com.project.stackoverflowjetpackcompose.paging

//import androidx.paging.PagingSource
//import com.project.stackoverflowjetpackcompose.model.Questions.Item
//import com.nhaarman.mockitokotlin2.doReturn
//import com.nhaarman.mockitokotlin2.mock
//import com.project.stackoverflowjetpackcompose.model.Questions.Questions
//import com.project.stackoverflowjetpackcompose.network.StackOverflowAPI
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.assertEquals
//import org.junit.Test
//import org.mockito.ArgumentMatchers.anyInt
//import org.mockito.ArgumentMatchers.anyString
//import org.mockito.Mockito.mock

//
//internal class StackSourceTest {
//    val item1:Item = Item(0,"abc","def",32,"n/a",12,true,12,12,"http:",
//    mock(),0,1,emptyList(),"Item1",34)
//
//
//    val item2:Item = Item(1,"abcdefg","defjdhkjh",33,"na",1,false,12,12,"http:",
//        mock(),0,1,emptyList(),"Item1",33)
//
//    val item3:Item = Item(1,"abcdefgbcdfk","defjdhkjhdwb",33,"nadsh",1,false,12,12,"http:",
//        mock(),0,1,emptyList(),"Item1",33)
//
// val items: List<Item> = listOf(item1,item2,item3)
//  val questionsItem = Questions(false,items,anyInt(),anyInt())
//
//    val apiMock = mock<StackOverflowAPI>(){
//      onBlocking{getQuestions(anyInt(),anyInt(),anyString(),anyString())} doReturn questionsItem
//    }
//
//    private fun <T> mock(clazz: Class<T>): T {
//
//    }
//
//    val pagingSource =
//        com.project.stackoverflowjetpackcompose.paging.StackSource(apiMock, "votes", "android")
//
//
//
//  @Test
//  fun SHOULD_display_pageOne() = runTest {
//    val actual = Unit
//    val expected = Unit
//    val pagingResult: Questions = apiMock.getQuestions(3,4,"votes","android")
//
//   val page = PagingSource.LoadResult.Page(
//     data = pagingResult.items,
//     prevKey = null,
//     nextKey = 2
//   )
//
//      val result = pagingSource.load(
//          PagingSource.LoadParams.Refresh(
//              key = null,
//              loadSize = 2,
//              placeholdersEnabled = false
//          )
//      )
//
//      assertEquals(page,result)
//
//  }
//}