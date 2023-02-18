package com.project.stackoverflowjetpackcompose.screens.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.stackoverflowjetpackcompose.model.Search.Item
import com.project.stackoverflowjetpackcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _sortSearch = mutableStateOf("creation")
    val sortSearch = _sortSearch

    fun updateSearchQuery(query:String){
        _searchQuery.value = query
    }

    fun updateSortParam(newSort:String) {
        _sortSearch.value = newSort
    }


    private val _searchQuestions : MutableStateFlow<PagingData<Item>> = MutableStateFlow(PagingData.empty())
    val searchQuestions = _searchQuestions

    fun fetchSearchQuestions(){
        viewModelScope.launch{
            repository.searchQuestions(sortSearch.value,intitle = searchQuery.value).cachedIn(viewModelScope).collect{
                Log.d("Sort Parameter",sortSearch.value)
                _searchQuestions.value = it
            }
        }
    }
}
