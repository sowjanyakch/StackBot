package com.example.stackoverflowjetpackcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stackoverflowjetpackcompose.ui.theme.Bronze
import com.example.stackoverflowjetpackcompose.ui.theme.Gold
import com.example.stackoverflowjetpackcompose.ui.theme.Silver
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun AuthorsDetails(image:String, displayName:String, reputation:Int, goldBadges:Int, silverBadges:Int, bronzeBadges:Int){

            GlideImage(modifier = Modifier
                .padding(end = 4.dp)
                .width(34.dp)
                .height(34.dp)
                .clip(CircleShape),
                imageModel = image,
                contentScale = ContentScale.Crop,
            )
        Column(){
            Text(text = displayName, fontSize = 12.sp, modifier = Modifier.padding(end = 1.dp) )
            Row(){
                Text(text = reputation.toString(),fontSize = 12.sp)
                Dot(Gold)
                Text(goldBadges.toString(),fontSize = 12.sp)
                Dot(Silver)
                Text(silverBadges.toString(),fontSize = 12.sp)
                Dot(Bronze)
                Text(bronzeBadges.toString(),fontSize = 12.sp)
            }
        }
    }

@Composable
fun Dot(color: Color){
    Box(
        modifier = Modifier.padding(start = 6.dp, top = 6.dp, bottom = 6.dp,end = 3.dp)
            .clip(CircleShape)
            .background(color)
            .size(5.dp)
    )
}
