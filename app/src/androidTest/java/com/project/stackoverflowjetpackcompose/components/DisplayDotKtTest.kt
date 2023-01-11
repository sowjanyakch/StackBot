package com.project.stackoverflowjetpackcompose.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

internal class DisplayDotKtTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun SHOULD_DISPLAY_Colour_Dot(){
        with(composeTestRule){
            setContent {
                com.project.stackoverflowjetpackcompose.components.DisplayDot(color = Color.Blue)
            }
            onNodeWithTag("dot").assertIsDisplayed()
        }
    }

}