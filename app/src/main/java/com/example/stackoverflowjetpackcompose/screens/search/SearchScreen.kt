package com.example.stackoverflowjetpackcompose.screens.search

import android.text.Html
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.stackoverflowjetpackcompose.components.AnswerCount
import com.example.stackoverflowjetpackcompose.components.AuthorsDetails
import com.example.stackoverflowjetpackcompose.model.Search.Item
import com.example.stackoverflowjetpackcompose.navigation.ScreensList

@OptIn(ExperimentalComposeUiApi::class)
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
                onSearchClick = { query ->
                        searchViewModel.fetchSearchQuestions(query)
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
                },fontSize = 16.sp,fontWeight = FontWeight.Medium)

            Row(modifier = Modifier.padding(top = 10.dp, bottom = 16.dp)){
                val authorDetails = item.owner
                val authorImage = authorDetails.profile_image
                val displayName = authorDetails.display_name
                val reputation = authorDetails.reputation
                val goldBadges = authorDetails.badge_counts.gold
                val silverBadges = authorDetails.badge_counts.silver
                val bronzeBadges = authorDetails.badge_counts.bronze

                AuthorsDetails(
                    image = authorImage,
                    displayName = displayName,
                    reputation = reputation,
                    goldBadges = goldBadges,
                    silverBadges = silverBadges ,
                    bronzeBadges = bronzeBadges
                )

                AnswerCount(modifier =Modifier.fillMaxSize(),imageVector = Icons.Outlined.QuestionAnswer, answerCount = item.answer_count.toString())
            }

        }
    }
}
