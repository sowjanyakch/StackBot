package com.project.stackoverflowjetpackcompose.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.project.stackoverflowjetpackcompose.navigation.ScreensList

// search screen implementation
@Composable

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel

) {
    val searchQuery by searchViewModel.searchQuery
    Scaffold(
        topBar = {
            DisplaySearchWidget(text = searchQuery,
                // update query on text change
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                // on search click, update query and get searched questions title list
                onSearchClick = { query ->
                    searchViewModel.fetchSearchQuestions(query)
                    navController.popBackStack()
                    navController.navigate(ScreensList.SearchQuestionsTitle.name)
                },
                onCloseClick = {
                    navController.popBackStack()
                })
        },
       content = { paddingValues ->
       }
    )


}




