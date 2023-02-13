package com.project.stackoverflowjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.stackoverflowjetpackcompose.screens.search.SearchScreen
import com.project.stackoverflowjetpackcompose.screens.splashScreen.SplashScreen
import com.project.stackoverflowjetpackcompose.model.BottomMenuBar
import com.project.stackoverflowjetpackcompose.screens.explore.ExploreScreen
import com.project.stackoverflowjetpackcompose.screens.explore.ExploreScreenViewModel
import com.project.stackoverflowjetpackcompose.screens.questionsDetails.DisplayQuestionsDetails
import com.project.stackoverflowjetpackcompose.screens.questionsDetails.QuestionsDetailsViewModel
import com.project.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel
import com.project.stackoverflowjetpackcompose.screens.search.SearchViewModel
import com.project.stackoverflowjetpackcompose.screens.searchQuestions.DisplayQuestionsTitleList
import com.project.stackoverflowjetpackcompose.tabs.HomeScreen
import com.project.stackoverflowjetpackcompose.tabs.PopularTags

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
                    questionsDetailsViewModel = questionsDetailsViewModel
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




