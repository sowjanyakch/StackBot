package com.example.stackoverflowjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stackoverflowjetpackcompose.screens.QuestionsDetails.QuestionsDetails
import com.example.stackoverflowjetpackcompose.screens.QuestionsDetails.QuestionsDetailsViewModel
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionUI
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionsTitleViewModel
import com.example.stackoverflowjetpackcompose.screens.SplashScreen.SplashScreen


@Composable


fun QuestionNavigation(questionsTitleViewModel: QuestionsTitleViewModel = hiltViewModel(),
questionsDetailsViewModel: QuestionsDetailsViewModel = hiltViewModel()){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensList.SplashScreen.name) {

        composable(ScreensList.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(ScreensList.QuestionsUI.name){
            QuestionUI(navController,questionsTitleViewModel)
        }
        val route = ScreensList.QuestionsDetailScreen.name
        composable("$route/{question_id}",
            arguments= listOf(navArgument(name = "question_id"){
                type = NavType.IntType
            })){
            navBack ->

            navBack.arguments?.getInt("question_id")?.let{
                item ->
                QuestionsDetails(item = item, questionsDetailsViewModel = questionsDetailsViewModel)
            }
        }

    }
}

