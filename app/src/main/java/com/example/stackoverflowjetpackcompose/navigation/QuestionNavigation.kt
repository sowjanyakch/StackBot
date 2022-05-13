package com.example.stackoverflowjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stackoverflowjetpackcompose.screens.QuestionsDetails.QuestionsDetails
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionUI
import com.example.stackoverflowjetpackcompose.screens.QuestionsTitle.QuestionsTitleViewModel
import com.example.stackoverflowjetpackcompose.screens.SplashScreen.SplashScreen


@Composable


fun QuestionNavigation(questionsTitleViewModel: QuestionsTitleViewModel = hiltViewModel()){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensList.SplashScreen.name) {

        composable(ScreensList.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(ScreensList.QuestionsUI.name){
            QuestionUI(navController,questionsTitleViewModel)
        }
        val route = ScreensList.QuestionsDetailScreen.name
        composable("$route/{item}",
            arguments= listOf(navArgument(name = "item"){
                type = NavType.inferFromValueType("item")
            })){
            navBack ->

            navBack.arguments?.get("item").let{
                item ->
                QuestionsDetails(item = item!!)
            }
        }
    }
}

