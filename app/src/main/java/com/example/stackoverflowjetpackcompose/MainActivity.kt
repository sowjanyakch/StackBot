package com.example.stackoverflowjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.stackoverflowjetpackcompose.navigation.QuestionNavigation
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionsTitleViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    val questionsViewModel:QuestionsTitleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           QuestionNavigation(questionsViewModel)
        }
    }
}



