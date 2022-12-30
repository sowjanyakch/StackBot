package com.example.stackoverflowjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stackoverflowjetpackcompose.model.BottomMenuBar
import com.example.stackoverflowjetpackcompose.screens.explore.ExploreScreen
import com.example.stackoverflowjetpackcompose.screens.explore.ExploreScreenViewModel
import com.example.stackoverflowjetpackcompose.screens.questionsDetails.DisplayQuestionsDetails
import com.example.stackoverflowjetpackcompose.screens.questionsDetails.QuestionsDetailsViewModel
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel
import com.example.stackoverflowjetpackcompose.screens.search.SearchScreen
import com.example.stackoverflowjetpackcompose.screens.search.SearchViewModel
import com.example.stackoverflowjetpackcompose.screens.searchQuestions.DisplayQuestionsTitleList
import com.example.stackoverflowjetpackcompose.screens.splashScreen.SplashScreen
import com.example.stackoverflowjetpackcompose.tabs.HomeScreen
import com.example.stackoverflowjetpackcompose.tabs.PopularTags

@Composable

fun StackBotNavigation(
    questionsTitleViewModel: QuestionsTitleViewModel = hiltViewModel(),
    questionsDetailsViewModel: QuestionsDetailsViewModel = hiltViewModel(),
    exploreScreenViewModel: ExploreScreenViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel()

) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensList.SplashScreen.name) {

        composable(ScreensList.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(BottomMenuBar.Home.route) {
            HomeScreen(
                navController = navController,
                questionsTitleViewModel = questionsTitleViewModel
            )
        }

        composable(BottomMenuBar.Explore.route) {
            ExploreScreen(
                navController,
                questionsTitleViewModel,
                exploreScreenViewModel = exploreScreenViewModel
            )
        }
        val route = ScreensList.QuestionsDetailScreen.name
        composable("$route/{question_id}",
            arguments = listOf(
                navArgument(name = "question_id") {
                    type = NavType.IntType
                }
            )) { navBack ->
            navBack.arguments?.getInt("question_id")?.let { questionId ->
                DisplayQuestionsDetails(navController = navController,
                    questionId,
                    questionsDetailsViewModel = questionsDetailsViewModel,
                    onBack = {
                        navController.popBackStack()
                        navController.navigate(BottomMenuBar.Home.route)
                    }
                )
            }
        }

        composable(ScreensList.PopularTagsScreen.name) {
            PopularTags(navController = navController,
                questionsTitleViewModel = questionsTitleViewModel,
                onBack = {
                    navController.popBackStack()
                    navController.navigate(BottomMenuBar.Explore.route)
                })
        }

        composable(ScreensList.SearchScreen.name) {
            SearchScreen(navController, searchViewModel)
        }

        composable(ScreensList.SearchQuestionsTitle.name) {
            DisplayQuestionsTitleList(
                navController = navController,
                searchViewModel = searchViewModel
            )
        }
    }
}




