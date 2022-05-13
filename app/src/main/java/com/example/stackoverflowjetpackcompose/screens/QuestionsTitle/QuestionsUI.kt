package com.example.stackoverflowjetpackcompose.screens.QuestionsTitle

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.stackoverflowjetpackcompose.model.Item
import com.example.stackoverflowjetpackcompose.navigation.ScreensList

@Composable

fun QuestionUI(navController: NavController, questionsViewModel: QuestionsTitleViewModel) {

   val getQuestions = questionsViewModel.getQuestions.collectAsLazyPagingItems()
    Log.d("Question UI1", "$getQuestions")
    ScreenContent(navController,getQuestions)
}

@Composable

fun ScreenContent(navController:NavController,item:LazyPagingItems<Item>){

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .clickable { },
        contentPadding = PaddingValues(all = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){
itemsIndexed(items = item
    ){index, item ->

        Text(text = item!!.title, modifier = Modifier.clickable{
            navController.popBackStack()
            navController.navigate(ScreensList.QuestionsDetailScreen.name + "/$item")
        })
        Text(text = item.answer_count.toString())
}
    }
    }





