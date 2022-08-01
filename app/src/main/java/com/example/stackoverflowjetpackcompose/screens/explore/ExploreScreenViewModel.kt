package com.example.stackoverflowjetpackcompose.screens.explore

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflowjetpackcompose.model.Tags.Item
import com.example.stackoverflowjetpackcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreScreenViewModel @Inject constructor (private val repository: Repository) : ViewModel() {

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


