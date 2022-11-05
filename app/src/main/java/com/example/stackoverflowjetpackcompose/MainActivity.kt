package com.example.stackoverflowjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import com.example.stackoverflowjetpackcompose.navigation.StackBotNavigation
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val questionsTitleViewModel by viewModels<QuestionsTitleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Color.White, darkIcons = true)
            systemUiController.setNavigationBarColor(Color.White)
            Log.d("Question Navigation", "Question Navigation call")
            StackBotNavigation(questionsTitleViewModel)
        }
    }
}



