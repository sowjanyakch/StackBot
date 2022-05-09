package com.example.stackoverflowjetpackcompose.screens.QuestionsTitle

import androidx.lifecycle.ViewModel
import com.example.stackoverflowjetpackcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionsTitleViewModel @Inject constructor (repository:Repository) : ViewModel() {
    val getQuestions = repository.getQuestions()
}