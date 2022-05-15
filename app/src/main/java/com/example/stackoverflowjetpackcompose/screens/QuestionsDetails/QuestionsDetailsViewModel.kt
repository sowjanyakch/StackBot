package com.example.stackoverflowjetpackcompose.screens.QuestionsDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.example.stackoverflowjetpackcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsDetailsViewModel @Inject constructor (private val repository: Repository) : ViewModel() {

  var questionbyId by mutableStateOf(QuestionItem(false,emptyList(),0,0))

        fun getQuestionsbyId(questionId:Int):QuestionItem{

            viewModelScope.launch{
                questionbyId = repository.getQuestionsbyid(questionId)

            }
            return questionbyId

        }
    }


