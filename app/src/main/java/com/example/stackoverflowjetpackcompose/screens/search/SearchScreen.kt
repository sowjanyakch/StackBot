package com.example.stackoverflowjetpackcompose.screens.search

import android.text.Html
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.stackoverflowjetpackcompose.model.Search.Item
import com.example.stackoverflowjetpackcompose.navigation.ScreensList

@Composable

fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel

){


    val searchQuery by searchViewModel.searchQuery

    val searchedQuestions = searchViewModel.searchQuestions.collectAsLazyPagingItems()

    Log.d("SearchQuestions","$searchedQuestions")

    Scaffold(
        topBar = {
            SearchWidget(text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query =it)

                },
                onSearchClick = {

                    searchViewModel.fetchSearchQuestions(it)

                },
                onCloseClick = {
                    navController.popBackStack()
                })
        },


        ){
        SearchQuestionsTitle(navController = navController, item = searchedQuestions)
    }
}


@Composable

fun SearchQuestionsTitle(navController: NavController, item: LazyPagingItems<Item>){

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .clickable { },
        contentPadding = PaddingValues(all = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){
        itemsIndexed(items = item
        ){index, item ->

            Text(text = Html.fromHtml(item!!.title).toString(),modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.popBackStack()
                    navController.navigate(ScreensList.QuestionsDetailScreen.name + "/${item.question_id}")
                })
            Text(text = item.answer_count.toString())
        }
    }
}
