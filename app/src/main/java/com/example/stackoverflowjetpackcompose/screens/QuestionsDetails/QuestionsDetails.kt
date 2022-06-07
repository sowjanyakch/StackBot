package com.example.stackoverflowjetpackcompose.screens.QuestionsDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem


@Composable

fun QuestionsDetails(questionId:Int, questionsDetailsViewModel: QuestionsDetailsViewModel,
     onBack:() -> Unit) {

    LaunchedEffect(key1 = questionId, block = {
        questionsDetailsViewModel.getQuestionsById(questionId)
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
        when (val viewState = questionsDetailsViewModel.viewState) {

            is QuestionDetailsViewState.None -> {

            }

            is QuestionDetailsViewState.Loading -> {
               Loading()

            }

            is QuestionDetailsViewState.Success -> {

                title.value = viewState.question.items.firstOrNull()?.title ?: ""
                QuestionDetails(question = viewState.question)
            }

            is QuestionDetailsViewState.Error -> {
              Error(message = viewState.message,onRetryClick = {
                  questionsDetailsViewModel.getQuestionsById(questionId)
              }
                )
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
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = question.items.first()?.body_markdown)
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


