package com.project.stackoverflowjetpackcompose.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

//internal class AuthorDetailsKtTest {
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun SHOULD_display_Author_details() {
//
//        with(composeTestRule) {
//            setContent {
//                com.project.stackoverflowjetpackcompose.components.AuthorDetails(
//                    authorImage = "Author1",
//                    displayName = "AuthorName",
//                    reputation = 1,
//                    goldBadges = 10,
//                    silverBadges = 100,
//                    bronzeBadges = 1000
//                )
//            }
//            onNodeWithText("10").assertIsDisplayed()
//            onNodeWithContentDescription("Author Image").assertIsDisplayed()
//        }
//    }
//}