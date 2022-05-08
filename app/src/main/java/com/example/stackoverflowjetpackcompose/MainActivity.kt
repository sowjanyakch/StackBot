package com.example.stackoverflowjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.stackoverflowjetpackcompose.model.Item
import com.example.stackoverflowjetpackcompose.navigation.QuestionNavigation
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionsTitleViewModel

class MainActivity : ComponentActivity() {
    val questionsViewModel by viewModels<QuestionsTitleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           QuestionNavigation(questionsViewModel)
        }
    }
}



