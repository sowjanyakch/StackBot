package com.project.stackoverflowjetpackcompose.screens.searchQuestions

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.project.stackoverflowjetpackcompose.model.Search.Item
import com.project.stackoverflowjetpackcompose.components.AuthorDetails
import com.project.stackoverflowjetpackcompose.components.DisplayAnswerCount
import com.project.stackoverflowjetpackcompose.navigation.ScreensList
import com.project.stackoverflowjetpackcompose.screens.search.SearchViewModel

@Composable

fun DisplayQuestionsTitleList(navController: NavController, searchViewModel: SearchViewModel){

    val searchQuery by searchViewModel.searchQuery


    Column{

            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(45.dp),
                color = Color(0xFFEAEFF2),
                border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
                contentColor = Color.Black,
                shape = CircleShape
            ){
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.navigate(ScreensList.SearchScreen.name)
                    }) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back Arrow", tint = Color.Black, modifier = Modifier.padding(start = 8.dp, end = 16.dp) )

                    }
                    Text(text = searchQuery)
                }

            }

        DisplaySortOptions(navController = navController,searchViewModel)
    }
}

@Composable

fun DisplayQuestionsTitle(navController: NavController, item: LazyPagingItems<Item>){

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .clickable { },
        contentPadding = PaddingValues(all = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){
        itemsIndexed(items = item
        ){_, item ->

            Text(text = HtmlCompat.fromHtml(item!!.title,HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),modifier = Modifier
                .fillMaxSize()
                .clickable {
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

                AuthorDetails(
                    authorImage = authorImage,
                    displayName = displayName,
                    reputation = reputation,
                    goldBadges = goldBadges,
                    silverBadges = silverBadges ,
                    bronzeBadges = bronzeBadges
                )
                DisplayAnswerCount(modifier = Modifier.fillMaxSize(),imageVector = Icons.Outlined.QuestionAnswer, answerCount = item.answer_count.toString())
            }

        }
    }
}


@Composable

fun DisplaySortOptions(navController: NavController, searchViewModel:SearchViewModel){


    val tabs = listOf(
        "Creation",
        "Relevance",
        "Activity",
        "Votes"
    )
    Column{
        LazyRow {
            itemsIndexed(tabs){_, tabs ->
                Button(onClick = {
                    searchViewModel.updateSortParam(tabs.lowercase())
                    searchViewModel.fetchSearchQuestions()
                },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White,
                        contentColor = Color.Blue),
                    modifier = Modifier.padding(8.dp)) {
                    Text(text = tabs)
                }
            }
        }


        val searchedQuestions = searchViewModel.searchQuestions.collectAsLazyPagingItems()
        DisplayQuestionsTitle(navController = navController, item = searchedQuestions)
    }
}

