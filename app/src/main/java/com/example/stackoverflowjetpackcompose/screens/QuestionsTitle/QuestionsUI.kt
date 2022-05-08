package com.example.stackoverflowjetpackcompose.screens.QuestionsTitle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.stackoverflowjetpackcompose.model.Item

@Composable

fun QuestionUI(questionsViewModel: QuestionsTitleViewModel) {

   val getQuestions = questionsViewModel.getQuestions.collectAsLazyPagingItems()
    ScreenContent(getQuestions)

}

@Composable

fun ScreenContent(item:LazyPagingItems<Item>){

    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)

    ){


   items(item.itemCount){

       Text(item.toString())

   }
    }

    }



