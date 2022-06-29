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

     val _questions: MutableStateFlow<PagingData<Item>> = MutableStateFlow(PagingData.empty())
   val questions = _questions


    private val _tagged = MutableStateFlow<String>("android")
    val tagged = _tagged

    init{
        fetchQuestions()
    }


    fun fetchQuestions() {
        viewModelScope.launch {

            repository.getQuestions(tagged.value).cachedIn(viewModelScope).collect{

                _questions.value = it
                Log.d("questions_value", "$_questions.value")
            }

        }
    }

    fun updateTag(newtag:String){
        _tagged.value = newtag
        Log.d("tag name","$_tagged.value")
        fetchQuestions()
    }


}