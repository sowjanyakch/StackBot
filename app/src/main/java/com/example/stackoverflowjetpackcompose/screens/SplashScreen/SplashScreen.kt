package com.example.stackoverflowjetpackcompose.screens.SplashScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.stackoverflowjetpackcompose.navigation.ScreensList

@Composable
fun SplashScreen(navController: NavController){
    Text("SplashScreen")
    navController.navigate(ScreensList.QuestionsUI.name)
}