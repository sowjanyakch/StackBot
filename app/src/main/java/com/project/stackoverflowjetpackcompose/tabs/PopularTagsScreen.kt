package com.project.stackoverflowjetpackcompose.tabs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.stackoverflowjetpackcompose.screens.questionsTitle.QuestionTitle
import com.project.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable

fun PopularTags(
    navController: NavController,
    questionsTitleViewModel: QuestionsTitleViewModel,
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = questionsTitleViewModel.tagged.value,
                    color = Color.Black
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
    ){padding ->
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
                itemsIndexed(tabs){_,tabs ->
                    Button(onClick = {
                        questionsTitleViewModel.updateTagSort(tabs.lowercase(),questionsTitleViewModel.tagged.value)
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

