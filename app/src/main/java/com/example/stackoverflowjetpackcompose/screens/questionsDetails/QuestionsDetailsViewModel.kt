package com.example.stackoverflowjetpackcompose.screens.questionsDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflowjetpackcompose.model.Answers.Answers
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.example.stackoverflowjetpackcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsDetailsViewModel @Inject constructor (private val repository: Repository) : ViewModel() {

    var viewState by mutableStateOf<QuestionDetailsViewState>(QuestionDetailsViewState.None)
    private set

    var answersViewState by mutableStateOf<AnswerDetailsViewState>(AnswerDetailsViewState.None)
        private set

  fun getQuestionsById(questionId:Int){
        viewModelScope.launch{
            viewState = QuestionDetailsViewState.Loading
            try{
                val response = repository.getQuestionsById(questionId)
                viewState = QuestionDetailsViewState.Success(response)
            }catch(exception:Exception){
                viewState = QuestionDetailsViewState.Error(exception.message)
                }
            }
        }

    fun getAnswersById(questionId:Int){
        viewModelScope.launch{
            answersViewState = AnswerDetailsViewState.Loading
            try{
                val answerResponse = repository.getAnswersById(questionId)
                answersViewState = AnswerDetailsViewState.Success(answerResponse)
            }catch(exception:Exception){
                answersViewState = AnswerDetailsViewState.Error(exception.message)
            }
        }
    }
    }


sealed class QuestionDetailsViewState{
    object None: QuestionDetailsViewState()
    object Loading:QuestionDetailsViewState()
    data class Success(val question:QuestionItem):QuestionDetailsViewState()
    data class Error(val message: String?):QuestionDetailsViewState()
}

sealed class AnswerDetailsViewState{
    object None: AnswerDetailsViewState()
    object Loading:AnswerDetailsViewState()
    data class Success(val answer: Answers):AnswerDetailsViewState()
    data class Error(val message: String?):AnswerDetailsViewState()
}

