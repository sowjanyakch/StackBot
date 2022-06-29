package com.example.stackoverflowjetpackcompose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Chip( tagName:String) {

        Surface(
            modifier = Modifier.padding(end = 8.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.Black),
        ) {
            Row(){
                Text(
                    text = tagName,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
            }







