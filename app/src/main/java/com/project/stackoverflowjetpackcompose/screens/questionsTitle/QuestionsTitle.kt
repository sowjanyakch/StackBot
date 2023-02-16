package com.project.stackoverflowjetpackcompose.screens.questionsTitle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.project.stackoverflowjetpackcompose.model.Questions.Item
import com.project.stackoverflowjetpackcompose.components.AuthorDetails
import com.project.stackoverflowjetpackcompose.components.DisplayAnswerCount
import com.project.stackoverflowjetpackcompose.navigation.ScreensList

@Composable

fun QuestionTitle(navController: NavController, questionsTitleViewModel: QuestionsTitleViewModel)
{
   val getQuestions = questionsTitleViewModel.questions.collectAsLazyPagingItems()
    DisplayQuestionsTitle(navController,getQuestions)
}


@Composable
fun DisplayQuestionsTitle(navController: NavController, item: LazyPagingItems<Item>){
    
    LazyColumn(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize(),
        contentPadding = PaddingValues(all = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(items = item
        ){_, item ->
            if(item!= null){
                Text(text = HtmlCompat.fromHtml(item.title,HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            navController.navigate(ScreensList.QuestionsDetailScreen.name + "/${item.question_id}")
                        }, fontSize = 16.sp, fontWeight = FontWeight.Medium
                )
                Row(modifier = Modifier.padding(top = 10.dp, bottom = 16.dp)) {
                    val authorImage = item.owner.profile_image
                    val authorDetails = item.owner
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
                        silverBadges = silverBadges,
                        bronzeBadges = bronzeBadges
                    )
                    DisplayAnswerCount(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Outlined.QuestionAnswer,
                        answerCount = item.answer_count.toString()
                    )
                }

            }


            }
    }
}










