package com.example.stackoverflowjetpackcompose.screens.questionsTitle

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.stackoverflowjetpackcompose.components.AuthorsDetails
import com.example.stackoverflowjetpackcompose.components.BottomMenu
import com.example.stackoverflowjetpackcompose.model.Questions.Item
import com.example.stackoverflowjetpackcompose.navigation.ScreensList

@Composable

fun QuestionUI(navController: NavController, questionsTitleViewModel: QuestionsTitleViewModel) {

   val getQuestions = questionsTitleViewModel.questions.collectAsLazyPagingItems()

    Scaffold(

        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Questions"
                )
            },
            )
        },

        bottomBar = {
            BottomMenu(navController = navController)
        },

    ){
          QuestionsTitleDisplay(navController,getQuestions)
    }
}

@Composable

fun QuestionsTitleDisplay(navController: NavController, item: LazyPagingItems<Item>){


    LazyColumn(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { },
        contentPadding = PaddingValues(all = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(items = item
        ){index, item ->

            Text(text = Html.fromHtml(item!!.title).toString(),
                 modifier = Modifier
                     .fillMaxSize()
                     .clickable {
                         navController.popBackStack()
                         navController.navigate(ScreensList.QuestionsDetailScreen.name + "/${item.question_id}")
                     }, fontSize = 16.sp, fontWeight = FontWeight.Medium
            )
            Row(modifier = Modifier.padding(top = 10.dp, bottom = 16.dp)){
                val authorImage = item.owner.profile_image
                val authorDetails = item.owner
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
                AnswerCount(modifier = Modifier.fillMaxSize(), imageVector = Icons.Outlined.QuestionAnswer, answerCount = item!!.answer_count.toString() )

            }
            }
    }
}

@Composable

fun AnswerCount(modifier:Modifier,
                imageVector: ImageVector,
                answerCount:String){

    Row(modifier = modifier.padding(end = 3.dp),
    horizontalArrangement = Arrangement.End
    ){
        Icon(imageVector = imageVector, contentDescription = "Answer Icon")
        Spacer(modifier = Modifier.padding(end = 4.dp))
        Text(text = answerCount, textAlign = TextAlign.End )
    }
}








