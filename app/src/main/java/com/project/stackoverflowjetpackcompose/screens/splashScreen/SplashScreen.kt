package com.project.stackoverflowjetpackcompose.screens.splashScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stackoverflowjetpackcompose.R
import com.project.stackoverflowjetpackcompose.model.BottomMenuBar
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {

        scale.animateTo(targetValue = 0.9f,
        animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            }
        ))
        delay(2000)
        navController.popBackStack()
        navController.navigate(BottomMenuBar.Home.route)

    })

    Surface(modifier = Modifier.padding(15.dp)
        .size(500.dp)
        .scale(scale.value), shape = CircleShape, color = Color.White,
        border = BorderStroke(width = 1.dp, color = Color.White)
    ){

        Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

            Image(painter = painterResource(id = R.drawable.app_icon), contentDescription = "Robot Icon" ,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(120.dp)
            )

            Text(text = "StackBot", style = MaterialTheme.typography.h5,
            color = Color.Red)

        }
    }





}