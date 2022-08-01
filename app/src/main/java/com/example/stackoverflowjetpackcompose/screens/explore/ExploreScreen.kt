package com.example.stackoverflowjetpackcompose.screens.explore

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.stackoverflowjetpackcompose.components.DisplayBottomMenu
import com.example.stackoverflowjetpackcompose.navigation.ScreensList
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable

fun ExploreScreen(navController:NavController, questionsTitleViewModel: QuestionsTitleViewModel, exploreScreenViewModel: ExploreScreenViewModel){

    val tags = exploreScreenViewModel.getTags


    Scaffold(
        bottomBar = {
                DisplayBottomMenu(navController = navController)

        }
    ) {

        Column(modifier = Modifier.padding(12.dp))
        {
                DisplaySearchBar(navController = navController)
                Spacer(modifier = Modifier.padding(40.dp))
                Text(text = "Popular Tags", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold,fontSize = 28.sp, color = Color.Black)
                Spacer(modifier = Modifier.padding(20.dp))

               FlowRow(crossAxisSpacing = 10.dp){
                for(items in tags){
                    val tagName = items.name
                    DisplayChip(navController= navController,tagName,questionsTitleViewModel)
                }
            }
        }
    }
  }


@Composable
fun DisplayChip(navController: NavController, tagName: String, questionsTitleViewModel: QuestionsTitleViewModel) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Row()
         {
            Text(
                text = tagName,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        questionsTitleViewModel.updateTagSort("",tagName)
                        //navController.popBackStack()
                        navController.navigate(ScreensList.PopularTagsScreen.name)
                    })
            )

        }
    }
}
    //
    @Composable
    fun DisplaySearchBar(navController: NavController) {

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color(0xFFEAEFF2),
            border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
            contentColor = Color.Black,
            shape = CircleShape
        ) {

            Row(modifier = Modifier.clickable{
                    navController.popBackStack()
                    navController.navigate(ScreensList.SearchScreen.name)

                }
                )
            {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search Icon", tint = Color.Black,
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp,bottom = 12.dp, end = 6.dp))
                Text(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp,bottom = 12.dp, end = 6.dp),
                    text = "Search here",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }























