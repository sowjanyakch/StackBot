package com.example.stackoverflowjetpackcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stackoverflowjetpackcompose.ui.theme.Bronze
import com.example.stackoverflowjetpackcompose.ui.theme.Gold
import com.example.stackoverflowjetpackcompose.ui.theme.Silver
import com.skydoves.landscapist.glide.GlideImage


// displays author details with reputation and the badges count
@Composable
fun AuthorDetails(
    authorImage: String,
    displayName: String,
    reputation: Int,
    goldBadges: Int,
    silverBadges: Int,
    bronzeBadges: Int
) {
    GlideImage(
        modifier = Modifier
            .padding(end = 4.dp)
            .width(34.dp)
            .height(34.dp)
            .clip(CircleShape),
        imageModel = authorImage,
        contentScale = ContentScale.Crop,
        contentDescription = "Author Image"
    )
    Column {
        Text(text = displayName, fontSize = 12.sp, modifier = Modifier.padding(end = 1.dp))
        Row {
            Text(text = reputation.toString(), fontSize = 12.sp)
            // display dot with gold color
            DisplayDot(Gold)
            // display gold badges count
            Text(goldBadges.toString(), fontSize = 12.sp)
            DisplayDot(Silver)
            Text(silverBadges.toString(), fontSize = 12.sp)
            DisplayDot(Bronze)
            Text(bronzeBadges.toString(), fontSize = 12.sp)
        }
    }
}

// displays dot - gold, silver and bronze indicating different badge colors
@Composable
internal fun DisplayDot(color: Color) {
    Box(
        modifier = Modifier
            .padding(start = 6.dp, top = 6.dp, bottom = 6.dp, end = 3.dp)
            .clip(CircleShape)
            .background(color)
            .size(5.dp)
            .testTag("dot")
    )
}


// displays answer icon and answer count
@Composable
internal fun DisplayAnswerCount(
    modifier: Modifier,
    imageVector: ImageVector,
    answerCount: String
) {
    Row(
        modifier = modifier.padding(end = 3.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(imageVector = imageVector, contentDescription = "Answer Icon")
        Spacer(modifier = Modifier.padding(end = 4.dp))
        Text(text = answerCount, textAlign = TextAlign.End)
    }
}