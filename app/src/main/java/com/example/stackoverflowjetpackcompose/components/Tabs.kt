package com.example.stackoverflowjetpackcompose.components

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable

fun Tabs(){
    var tabIndex by remember{mutableStateOf(0)}

    val tabTitle = listOf("Hot","This Week")

    TabRow(selectedTabIndex = tabIndex ){
        tabTitle.forEachIndexed { index, title ->

            Tab(selected = tabIndex == index, onClick = {
           tabIndex = index
            },text = {
                Text(text = title)
            })

        }
    }

    when(tabIndex){
        0 ->
            1 ->
    }

}