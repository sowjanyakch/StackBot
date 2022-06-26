package com.example.stackoverflowjetpackcompose.screens.search

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stackoverflowjetpackcompose.components.BottomMenu
import com.example.stackoverflowjetpackcompose.model.BottomMenuBar
import com.example.stackoverflowjetpackcompose.navigation.ScreensList
import com.example.stackoverflowjetpackcompose.screens.explore.ExploreScreenViewModel
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable

fun ExploreScreen(navController:NavController,questionsTitleViewModel: QuestionsTitleViewModel,exploreScreenViewModel: ExploreScreenViewModel){

    val tags = exploreScreenViewModel.getTags


    Scaffold(
        bottomBar = {
                BottomMenu(navController = navController)

        }
    ) {

        Column(modifier = Modifier.padding(12.dp))
        {
                SearchBar(navController = navController)
                Spacer(modifier = Modifier.padding(30.dp))
                Text(text = "Popular Tags", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold,fontSize = 28.sp, color = Color.Black)
                Spacer(modifier = Modifier.padding(20.dp))

               FlowRow(crossAxisSpacing = 10.dp){
                for(items in tags){
                    val tagName = items.name
                    Chip(navController= navController,tagName,questionsTitleViewModel)
                }
            }
        }
    }
  }


@Composable
fun Chip(navController: NavController, tagName: String,questionsTitleViewModel: QuestionsTitleViewModel) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        color = Color.Blue
    ) {
        Row()
         {
            Text(
                text = tagName,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        questionsTitleViewModel.updateTag(tagName)
                        Log.d("tag name", "$tagName")
                        //navController.popBackStack()
                        navController.navigate(BottomMenuBar.Home.route)
                    })
            )

        }
    }
}

    @Composable
    fun SearchBar(navController: NavController) {

        Surface(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth().clickable{
                    navController.popBackStack()
                    navController.navigate(ScreensList.SearchScreen.name)
                } ,
            
            border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
            contentColor = Color.LightGray,
            elevation = 10.dp,
            shape = CircleShape
        ) {

            Row(modifier = Modifier
                .padding(10.dp)
                )
            {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search Icon")
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Search here",
                    fontSize = 18.sp,
                    color = Color.LightGray
                )
            }
        }
    }























