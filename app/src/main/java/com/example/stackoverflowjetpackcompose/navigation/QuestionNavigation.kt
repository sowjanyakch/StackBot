package com.example.stackoverflowjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionUI
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionsTitleViewModel
import com.example.stackoverflowjetpackcompose.screens.SplashScreen.SplashScreen


@Composable

fun QuestionNavigation(questionsViewModel: QuestionsTitleViewModel){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensList.SplashScreen.name) {

        composable(ScreensList.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(ScreensList.QuestionsUI.name){
            QuestionUI(questionsViewModel)
        }
    }
}
