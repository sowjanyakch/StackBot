package com.example.stackoverflowjetpackcompose.screens.QuestionsTitle

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.stackoverflowjetpackcompose.model.Item

@Composable

fun QuestionUI(questionsViewModel: QuestionsTitleViewModel= hiltViewModel()) {

   val getQuestions = questionsViewModel.getQuestions.collectAsLazyPagingItems()
    Log.d("Question UI1", "$getQuestions")
    ScreenContent(getQuestions)
}

@Composable

fun ScreenContent(item:LazyPagingItems<Item>){

    LazyColumn(modifier = Modifier.fillMaxSize().clickable { },
        contentPadding = PaddingValues(all = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){
itemsIndexed(items = item,
    ){index, item ->

    if (item != null) {
        Text(text = item.title)
        Text(text = item.answer_count.toString())
    }else{
        CircularProgressIndicator()
    }
}
    }
    }



