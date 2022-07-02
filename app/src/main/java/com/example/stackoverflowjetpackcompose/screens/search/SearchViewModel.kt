package com.example.stackoverflowjetpackcompose.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.stackoverflowjetpackcompose.model.Search.Item
import com.example.stackoverflowjetpackcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class SearchViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _sortSearch = mutableStateOf("activity")
    val sortSearch = _sortSearch

    fun updateSearchQuery(query:String){
        _searchQuery.value = query
    }

    val _searchQuestions : MutableStateFlow<PagingData<Item>> = MutableStateFlow(PagingData.empty())
    val searchQuestions = _searchQuestions

    fun fetchSearchQuestions(query:String){
        viewModelScope.launch{
            repository.searchQuestions(sortSearch.value,intitle = query).cachedIn(viewModelScope).collect{
                _searchQuestions.value = it
            }
        }
    }

}
