package com.example.stackoverflowjetpackcompose.Tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.stackoverflowjetpackcompose.components.BottomMenu
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionUI
import com.example.stackoverflowjetpackcompose.screens.questionsTitle.QuestionsTitleViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable

fun HomeTabs(navController: NavController, questionsTitleViewModel: QuestionsTitleViewModel){
    var scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pages = listOf(
        "Hot",
        "Week",
        "Month",
        "Votes",
        "Creation",
        "Activity"

    )

    Scaffold(

        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Questions",color = Color.Black
                )
            },
                backgroundColor = Color.White
            )
        },

        bottomBar = {
            BottomMenu(navController = navController)
        },){
        Column(){
            ScrollableTabRow(selectedTabIndex = pagerState.currentPage,
                backgroundColor = Color.White,
                contentColor = Color.Blue,
                indicator = {tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .fillMaxWidth()
                            .pagerTabIndicatorOffset(pagerState, tabPositions)
                    )

                }){

                pages.forEachIndexed{index, title ->
                    Tab(
                        text = {Text(title)},
                        selected = pagerState.currentPage == index,
                        onClick ={
                            scope.launch{
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )

                }

            }
            HorizontalPager(count = pages.size,modifier = Modifier.fillMaxSize(), state = pagerState) {page ->
                when(page){

                    0 -> {
                        questionsTitleViewModel.updateTagSort("hot","")
                        QuestionUI(navController = navController, questionsTitleViewModel = questionsTitleViewModel)
                    }
                    1 -> {
                        questionsTitleViewModel.updateTagSort("week","")
                        QuestionUI(navController = navController, questionsTitleViewModel = questionsTitleViewModel)
                    }
                    2 -> {
                        questionsTitleViewModel.updateTagSort("month","")
                        QuestionUI(navController = navController, questionsTitleViewModel = questionsTitleViewModel)
                    }
                    3 ->
                    {
                        questionsTitleViewModel.updateTagSort("votes","")
                        QuestionUI(navController = navController, questionsTitleViewModel = questionsTitleViewModel)
                    }
                    4 ->{
                        questionsTitleViewModel.updateTagSort("creation","")
                        QuestionUI(navController = navController, questionsTitleViewModel = questionsTitleViewModel)
                    }
                    5 ->{
                        questionsTitleViewModel.updateTagSort("activity","")
                        QuestionUI(navController = navController, questionsTitleViewModel = questionsTitleViewModel)
                    }
                }
            }

        }


    }

}

