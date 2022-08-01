package com.example.stackoverflowjetpackcompose.screens.questionsDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.example.stackoverflowjetpackcompose.components.DisplayAuthorDetails
import com.example.stackoverflowjetpackcompose.components.DisplayChip
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.google.accompanist.flowlayout.FlowRow
import com.mukesh.MarkDown


@Composable

fun DisplayQuestionsDetails(navController: NavController, questionId:Int, questionsDetailsViewModel: QuestionsDetailsViewModel,
                            onBack:() -> Unit) {
    LaunchedEffect(key1 = questionId, block = {
        questionsDetailsViewModel.getQuestionsById(questionId)
        questionsDetailsViewModel.getAnswersById(questionId)
    })
    val title = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = HtmlCompat.fromHtml(title.value, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                backgroundColor = Color.White
            )
        },

    ) {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
             item {
                 QuestionView(questionId, questionsDetailsViewModel, updateTitle = {
                     title.value = it
                 } )
             }
                     when (val answerViewState = questionsDetailsViewModel.answersViewState) {
                         is AnswerDetailsViewState.None -> {

                         }
                         is AnswerDetailsViewState.Loading -> {
                             item{Loading()}
                         }
                         is AnswerDetailsViewState.Success -> {

                             itemsIndexed(items = answerViewState.answer.items){_,item ->

                                 Row(modifier = Modifier.padding(start = 12.dp,top = 28.dp,bottom = 12.dp)){
                                     if(item.is_accepted) {
                                         Icon(
                                             Icons.Outlined.Check,
                                             contentDescription = "Answer Accepted",
                                             tint = Color.Green
                                         )
                                     }
                                         Text(item.score.toString(), modifier = Modifier.padding(end = 5.dp),fontWeight = FontWeight.Medium, fontSize = 18.sp)
                                         Text("Votes", fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 6.dp),fontSize = 18.sp)
                                     }
                                 MarkDown(
                                     modifier = Modifier
                                         .wrapContentHeight()
                                         .fillMaxSize(),
                                     text = item.body.trim()
                                 )
                                 Row{
                                     Spacer(modifier = Modifier.padding(start = 12.dp))
                                     DisplayAuthorDetails(
                                         authorImage = item.owner.profile_image,
                                         displayName = item.owner.display_name ,
                                         reputation = item.owner.reputation,
                                         goldBadges = item.owner.badge_counts.gold,
                                         silverBadges = item.owner.badge_counts.silver,
                                         bronzeBadges = item.owner.badge_counts.bronze
                                     )
                                 }
                                 Spacer(modifier = Modifier.padding(bottom = 24.dp))
                             }
                         }
                         is AnswerDetailsViewState.Error -> {

                             item{
                                 Error(message = answerViewState.message,
                                     onRetryClick = {
                                         questionsDetailsViewModel.getAnswersById(questionId)
                                     }
                                 )
                             }
                         }
                     }
                 }
    }
}


        @Composable
        fun Loading() {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        @Composable
        fun QuestionDetails(question: QuestionItem) {
            Column {
                val questionItem = question.items.first()
               val questionBody = questionItem.body
                val authorDetails = questionItem.owner
                Text(text = questionItem.title, style = MaterialTheme.typography.body1, fontSize = 18.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(12.dp))
                MarkDown(modifier = Modifier.fillMaxSize().padding(top = 6.dp),text = questionBody)
                FlowRow(crossAxisSpacing = 10.dp, modifier = Modifier.padding(start = 12.dp, top = 16.dp, bottom = 16.dp)){
                    for(items in questionItem.tags)
                        DisplayChip(items)
                }
                Row{
                    Spacer(modifier = Modifier.padding(start = 16.dp,bottom = 16.dp))
                    DisplayAuthorDetails(
                        authorImage = authorDetails.profile_image,
                        displayName = authorDetails.display_name,
                        reputation = authorDetails.reputation,
                        goldBadges = authorDetails.badge_counts.gold ,
                        silverBadges = authorDetails.badge_counts.silver ,
                        bronzeBadges = authorDetails.badge_counts.bronze
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(text = questionItem.answer_count.toString() + " " + "Answers", style = MaterialTheme.typography.subtitle2,
                    fontSize = 18.sp, fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 12.dp, bottom = 24.dp,top = 24.dp))
            }
        }

       @Composable

       fun QuestionView(
           questionId:Int,
           questionsDetailsViewModel:QuestionsDetailsViewModel,
           updateTitle:(title:String) ->Unit
       ){
           when (val viewState = questionsDetailsViewModel.viewState) {

               is QuestionDetailsViewState.None -> {

               }
               is QuestionDetailsViewState.Loading -> {
                   Loading()
               }
               is QuestionDetailsViewState.Success -> {

                   updateTitle.invoke(viewState.question.items.firstOrNull()?.score.toString() + "   "+ "Votes" )

                   Surface(modifier = Modifier.padding(top = 10.dp)) {
                       QuestionDetails(question = viewState.question)
                   }
               }
               is QuestionDetailsViewState.Error -> {
                   Error(message = viewState.message, onRetryClick = {
                       questionsDetailsViewModel.getQuestionsById(questionId)
                   }
                   )
               }
           }
       }


     @Composable
       fun Error(
            message: String?,
            onRetryClick: () -> Unit
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = message ?: "An Unknown issue occurred")
                Button(onClick = onRetryClick) {
                    Text(text = "Retry")
                }
            }
        }


