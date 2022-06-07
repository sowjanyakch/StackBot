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

    var viewState by mutableStateOf<QuestionDetailsViewState>(QuestionDetailsViewState.None)
    private set

    fun getQuestionsById(questionId:Int){
        viewModelScope.launch{
            viewState = QuestionDetailsViewState.Loading
            try{
                val response = repository.getQuestionsbyid(questionId)
                viewState = QuestionDetailsViewState.Success(response)
            }catch(exception:Exception){
                viewState = QuestionDetailsViewState.Error(exception.message)
                }
            }
        }
    }


sealed class QuestionDetailsViewState{
    object None: QuestionDetailsViewState()
    object Loading:QuestionDetailsViewState()
    class Success(val question:QuestionItem):QuestionDetailsViewState()
    class Error(val message: String?):QuestionDetailsViewState()
}

