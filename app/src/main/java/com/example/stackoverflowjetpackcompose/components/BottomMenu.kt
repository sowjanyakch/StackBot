package com.example.stackoverflowjetpackcompose.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.stackoverflowjetpackcompose.R
import com.example.stackoverflowjetpackcompose.model.BottomMenuBar

@Composable

fun BottomMenu(navController: NavController){
    val items = listOf(
        BottomMenuBar.Home,
        BottomMenuBar.Explore
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    BottomNavigation(backgroundColor = Color.White,contentColor = colorResource(id = R.color.white)) {
        items.forEach{
           BottomNavigationItem(
               onClick = {
                    navController.navigate(it.route){
                        navController.graph.startDestinationRoute?.let{route ->
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

               },
               label ={Text(text = it.title)},
               selected = currentDestination == it.route,
               selectedContentColor = Color.Blue,
               unselectedContentColor = Color.Black,

               alwaysShowLabel = true,
               icon = {
                   Icon(imageVector = it.icon,
                   contentDescription = it.title)
               }
           )
        }
    }
}