package com.example.stackoverflowjetpackcompose.screens.splashScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.stackoverflowjetpackcompose.model.BottomMenuBar
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    Text("SplashScreen")
    LaunchedEffect(Unit){
        delay(2000)
        navController.popBackStack()
        navController.navigate(BottomMenuBar.Home.route)
    }
}