package com.project.stackoverflowjetpackcompose.tabs

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.stackoverflowjetpackcompose.components.DisplayBottomMenu
import com.project.stackoverflowjetpackcompose.screens.questionsTitle.QuestionTitle
import com.project.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController:NavController, questionsTitleViewModel: QuestionsTitleViewModel){

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Questions")
            },
            backgroundColor = Color.White
        )
    },
        bottomBar = {
            DisplayBottomMenu(navController = navController)
        }){ padding->

        Column {
            val tabs = listOf(
                "Hot",
                "Week",
                "Month",
                "Votes",
                "Creation",
                "Activity",
            )

            LazyRow {
                itemsIndexed(tabs){_, tabs ->
                    Button(onClick = {
                        questionsTitleViewModel.updateTagSort(tabs.lowercase(),"")
                    },
                        shape = RoundedCornerShape(3.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White,
                            contentColor = Color.Blue),
                        modifier = Modifier.padding(10.dp)) {
                        Text(text = tabs)

                    }
                }
            }
            QuestionTitle(navController = navController, questionsTitleViewModel = questionsTitleViewModel)
        }
    }
}
