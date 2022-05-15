package com.example.stackoverflowjetpackcompose.screens.QuestionsDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.stackoverflowjetpackcompose.model.QuestionId.QuestionItem

@Composable

 fun QuestionsDetails(item:Int, questionsDetailsViewModel: QuestionsDetailsViewModel){


var questionbyId by remember{
    mutableStateOf(QuestionItem(false, emptyList(), 0,0))
}
    questionbyId = questionsDetailsViewModel.getQuestionsbyId(item)
Column{

    Text(questionbyId.has_more.toString())
    Text(questionbyId.quota_remaining.toString())
    Text(questionbyId.quota_max.toString())
    Text(questionbyId.items[0].answer_count.toString())
}

}