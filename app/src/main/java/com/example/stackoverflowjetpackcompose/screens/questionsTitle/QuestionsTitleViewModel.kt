package com.example.stackoverflowjetpackcompose.screens.questionsTitle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.stackoverflowjetpackcompose.model.Questions.Item
import com.example.stackoverflowjetpackcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionsTitleViewModel @Inject constructor (private val repository:Repository) : ViewModel() {

   private val _questions: MutableStateFlow<PagingData<Item>> = MutableStateFlow(PagingData.empty())
    val questions = _questions

    private val _sort = MutableStateFlow("votes")
    val sort = _sort

    private val _tagged = MutableStateFlow("android")
    val tagged = _tagged

    init{
        fetchQuestions()
    }


    fun fetchQuestions() {
        viewModelScope.launch {
            repository.getQuestions(sort.value,tagged.value).cachedIn(viewModelScope).collect{
                _questions.value = it
                Log.d("questions_value", "$_questions.value")
            }
        }
    }

    fun updateTagSort(newSort:String, newTag:String){
        _sort.value = newSort
        _tagged.value = newTag
        Log.d("tag name","$_tagged.value")
        fetchQuestions()
    }


}