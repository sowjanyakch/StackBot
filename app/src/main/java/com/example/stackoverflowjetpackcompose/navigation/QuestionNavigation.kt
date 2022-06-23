package com.example.stackoverflowjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stackoverflowjetpackcompose.model.BottomMenuBar
import com.example.stackoverflowjetpackcompose.screens.questionsDetails.QuestionsDetails
import com.example.stackoverflowjetpackcompose.screens.questionsDetails.QuestionsDetailsViewModel
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionUI
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel
import com.example.stackoverflowjetpackcompose.screens.search.ExploreScreen
import com.example.stackoverflowjetpackcompose.screens.search.ExploreScreenViewModel
import com.example.stackoverflowjetpackcompose.screens.splashScreen.SplashScreen

@Composable

fun QuestionNavigation(questionsTitleViewModel: QuestionsTitleViewModel = hiltViewModel(),
questionsDetailsViewModel: QuestionsDetailsViewModel = hiltViewModel(),
exploreScreenViewModel: ExploreScreenViewModel = hiltViewModel()){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensList.SplashScreen.name) {

        composable(ScreensList.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(BottomMenuBar.Home.route){
            QuestionUI(navController,questionsTitleViewModel)
        }

        composable(BottomMenuBar.Explore.route){
           ExploreScreen(navController, exploreScreenViewModel = exploreScreenViewModel)
        }
        val route = ScreensList.QuestionsDetailScreen.name
        composable("$route/{question_id}",
            arguments= listOf(
                navArgument(name = "question_id"){
                type = NavType.IntType
            }
            )){
            navBack ->

            navBack.arguments?.getInt("question_id")?.let{ questionId ->
                QuestionsDetails(navController = navController,questionId, questionsDetailsViewModel = questionsDetailsViewModel,
                onBack = {
                    navController.popBackStack()
                    navController.navigate(BottomMenuBar.Home.route )
                }
                    )
            }
        }
    }
}




