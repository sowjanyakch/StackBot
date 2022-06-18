package com.example.stackoverflowjetpackcompose.screens.questionsDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.stackoverflowjetpackcompose.model.Answers.Answers
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.mukesh.MarkDown


@Composable

fun QuestionsDetails(questionId:Int, questionsDetailsViewModel: QuestionsDetailsViewModel,
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
                    text = title.value,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn {
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
                            item{
                                Text(
                                    modifier = Modifier.padding(10.dp),
                                    text = "Answers",
                                    style = MaterialTheme.typography.subtitle2
                                )
                            }

                             itemsIndexed(items = answerViewState.answer.items){index,item ->
                                 Spacer(modifier = Modifier.padding(vertical = 10.dp))
                                 MarkDown(
                                     modifier = Modifier.wrapContentHeight(),
                                     text = item.body.trim()
                                 )
                                 Divider(thickness = 2.dp)
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
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
               val questionBody = question.items.first()?.body
                MarkDown(text = questionBody)
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

                   updateTitle.invoke(viewState.question.items.firstOrNull()?.title ?: "")
                   Surface(modifier = Modifier.padding(10.dp)) {
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

fun AnswerBody(answer: Answers){
    val answerParameters = answer.items
   LazyColumn(modifier = Modifier
        .padding(10.dp)
        ){
       itemsIndexed(items = answerParameters){index, item ->
           val answerBody = item.body
          MarkDown(text = answerBody)
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


