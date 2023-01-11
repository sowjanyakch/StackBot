package com.project.stackoverflowjetpackcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.stackoverflowjetpackcompose.model.Search.Item
import com.project.stackoverflowjetpackcompose.network.StackOverflowAPI
import com.project.stackoverflowjetpackcompose.utils.Constants.ITEMS_PER_PAGE

class SearchPagingSource(
    private val stackOverflowAPI: StackOverflowAPI,
    private val sort: String,
    private val intitle: String
) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val currentPage = params.key ?: 1
        return try {
            val response =
                stackOverflowAPI.searchQuestion(currentPage, ITEMS_PER_PAGE, sort, intitle)
            val endOfPagination = response.items.isEmpty()

            if (response.has_more == true) {
                LoadResult.Page(
                    data = response.items,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPagination) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

