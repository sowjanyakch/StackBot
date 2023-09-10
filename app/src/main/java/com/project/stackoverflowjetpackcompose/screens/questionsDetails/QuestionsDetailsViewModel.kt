package com.project.stackoverflowjetpackcompose.screens.questionsDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.stackoverflowjetpackcompose.model.Answers.Answers
import com.project.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.project.stackoverflowjetpackcompose.repository.StackOverflowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsDetailsViewModel @Inject constructor (private val repository: StackOverflowRepository) : ViewModel() {

    var questionsViewState by mutableStateOf<QuestionDetailsViewState>(QuestionDetailsViewState.None)
    private set

    var answersViewState by mutableStateOf<AnswerDetailsViewState>(AnswerDetailsViewState.None)
        private set

  fun getQuestionsById(questionId:Int){
      questionsViewState = QuestionDetailsViewState.Loading
        val questionDeferred = viewModelScope.async{
            repository.getQuestionsById(questionId)
        }

      viewModelScope.launch{
          try{
              val questions = questionDeferred.await()
              questionsViewState = QuestionDetailsViewState.Success(questions)
          }catch(exception:Exception){
              questionsViewState = QuestionDetailsViewState.Error(exception.message)
          }
      }
        }

    fun getAnswersById(questionId:Int){
        answersViewState = AnswerDetailsViewState.Loading
        val answerDeferred = viewModelScope.async{
            repository.getAnswersById(questionId)
        }
        viewModelScope.launch{
            try{
                val answers = answerDeferred.await()
                answersViewState = AnswerDetailsViewState.Success(answers)
            }catch(exception:Exception){
                answersViewState = AnswerDetailsViewState.Error(exception.message)
            }
        }
    }
    }


sealed class QuestionDetailsViewState{
    object None: QuestionDetailsViewState()
    object Loading:QuestionDetailsViewState()
    data class Success(val question: QuestionItem):QuestionDetailsViewState()
    data class Error(val message: String?):QuestionDetailsViewState()
}

sealed class AnswerDetailsViewState{
    object None: AnswerDetailsViewState()
    object Loading:AnswerDetailsViewState()
    data class Success(val answer: Answers):AnswerDetailsViewState()
    data class Error(val message: String?):AnswerDetailsViewState()
}

