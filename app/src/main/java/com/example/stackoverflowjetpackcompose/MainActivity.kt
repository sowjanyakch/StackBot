package com.example.stackoverflowjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.stackoverflowjetpackcompose.navigation.QuestionNavigation
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class MainActivity : ComponentActivity() {

    private val questionsTitleViewModel by viewModels<QuestionsTitleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Log.d("Question Navigation","Question Navigation call" )
           QuestionNavigation(questionsTitleViewModel)
        }
    }
}



