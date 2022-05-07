package com.example.stackoverflowjetpackcompose.screens.QuestionsTitle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stackoverflowjetpackcompose.model.TopQuestions

@Composable

fun QuestionUI(questionsViewModel: QuestionsTitleViewModel) {

    val questionData =  remember { questionsViewModel.questionItem }

    ScreenContent(data = questionData)
}





@Composable

fun ScreenContent(data : TopQuestions){

    var itemValues = data.items

    LazyColumn(

    ){
        itemsIndexed(itemValues){index, item ->

            Card(
                modifier = Modifier
                    .padding(8.dp, 4.dp)
                    .fillMaxWidth()
                    .height(20.dp)

            ){
                Column(){

                    Text(item.title)
                    Text(item.answer_count.toString())

                }

            }

        }

    }

}
