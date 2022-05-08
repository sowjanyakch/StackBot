package com.example.stackoverflowjetpackcompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.stackoverflowjetpackcompose.model.Item
import com.example.stackoverflowjetpackcompose.network.StackOverflowAPI
import com.example.stackoverflowjetpackcompose.network.StackOverflowAPIService
import com.example.stackoverflowjetpackcompose.paging.StackSource
import com.example.stackoverflowjetpackcompose.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class Repository(){

    private val api: StackOverflowAPIService = StackOverflowAPI.retrofitService
    fun getQuestions():Flow<PagingData<Item>>{

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                StackSource(api)
            }
        ).flow
    }
}

