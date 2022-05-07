package com.example.stackoverflowjetpackcompose.screens.QuestionsTitle

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflowjetpackcompose.model.TopQuestions
import com.example.stackoverflowjetpackcompose.network.StackOverflowAPI
import com.example.stackoverflowjetpackcompose.network.StackOverflowAPIService
import kotlinx.coroutines.launch

class QuestionsTitleViewModel : ViewModel() {


    private val api: StackOverflowAPIService = StackOverflowAPI.retrofitService

    var questionItem by mutableStateOf(TopQuestions(true, emptyList(),0,0,0))

    init {

        fetchQuestions()


    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            try {
                questionItem = api.getQuestions(1)
                Log.d("API","API called")

            }catch(e:Exception){

                Log.d("VM-fetchQuestions",e.toString())
            }
        }

    }
}