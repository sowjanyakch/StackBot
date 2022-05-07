package com.example.stackoverflowjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.stackoverflowjetpackcompose.navigation.QuestionNavigation
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionsTitleViewModel
import com.example.stackoverflowjetpackcompose.ui.theme.StackOverflowJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val questionsViewModel by viewModels<QuestionsTitleViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            StackOverflowJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StackQuestions(questionsViewModel)
                }
            }
        }
    }
}

@Composable

fun StackQuestions(questionsViewModel: QuestionsTitleViewModel){
    val questionItem = remember {
        questionsViewModel.questionItem
    }
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.White){
        QuestionNavigation(questionsViewModel)
    }
}

