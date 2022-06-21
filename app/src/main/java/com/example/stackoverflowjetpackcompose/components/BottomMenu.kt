package com.example.stackoverflowjetpackcompose.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.stackoverflowjetpackcompose.R
import com.example.stackoverflowjetpackcompose.model.BottomMenuBar

@Composable

fun BottomMenu(){
    val items = listOf(
        BottomMenuBar.Home,
        BottomMenuBar.Search
    )
    BottomNavigation(contentColor = colorResource(id = R.color.white)) {
        items.forEach{
           BottomNavigationItem(selected = false,
               onClick = {

               },
               label ={Text(text = it.title)},
               alwaysShowLabel = true,
               icon = {
                   Icon(imageVector = it.icon,
                   contentDescription = it.title)
               }
           )
        }
    }
}