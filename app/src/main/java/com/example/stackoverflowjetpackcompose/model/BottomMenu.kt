package com.example.stackoverflowjetpackcompose.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuBar(val route:String, val icon:ImageVector, val title:String){

    object Home: BottomMenuBar("home", Icons.Outlined.Home,"Home")
    object Search:BottomMenuBar("search",Icons.Outlined.Search,"Search")
}
