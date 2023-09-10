package com.project.stackoverflowjetpackcompose.screens.explore

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.stackoverflowjetpackcompose.model.Tags.Item
import com.project.stackoverflowjetpackcompose.repository.StackOverflowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreScreenViewModel @Inject constructor(private val repository: StackOverflowRepository) : ViewModel() {

  var getTags: List<Item> by mutableStateOf(emptyList())

  init{
    fetchTags()
  }

  private fun fetchTags(){
    viewModelScope.launch{
      try{
        getTags = repository.popularTags().items
      }catch(e:Exception){
        Log.d("Error",e.toString())
      }
    }
  }

}


