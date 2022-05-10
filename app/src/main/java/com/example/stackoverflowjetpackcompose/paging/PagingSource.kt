package com.example.stackoverflowjetpackcompose.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.stackoverflowjetpackcompose.model.Item
import com.example.stackoverflowjetpackcompose.network.StackOverflowAPI


class StackSource(
    private val stackOverflowAPI: StackOverflowAPI
): PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val currentPage = params.key?:1
        Log.d("Paging Source","$currentPage")


        return try
        {
            val stackResponse = stackOverflowAPI.getQuestions(currentPage)
            val endOfPaginationReached = stackResponse.items.isEmpty()
            if(stackResponse.has_more == true){
              LoadResult.Page(
                    data = stackResponse.items,
                    prevKey = if(currentPage == 1) null else currentPage-1,
                    nextKey = if(endOfPaginationReached) null else currentPage+1
                )

            }else{
               LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }catch(e:Exception){
            LoadResult.Error(e)
        }
    }

}
