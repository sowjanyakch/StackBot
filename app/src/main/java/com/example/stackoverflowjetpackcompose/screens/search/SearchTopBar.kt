

package com.example.stackoverflowjetpackcompose.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun DisplaySearchWidget(
    text:String,
    onTextChange:(String) -> Unit,
    onSearchClick:(String) -> Unit,
    onCloseClick:() ->Unit
){
    val focusRequester = remember{ FocusRequester() }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.onPrimary
    ){
        LaunchedEffect(Unit){
           focusRequester.requestFocus()
        }
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField( modifier = Modifier.fillMaxWidth().focusRequester (focusRequester).onFocusChanged{

        }  ,
            value = text,
            onValueChange = {onTextChange(it)},
            placeholder = {
                Text(text = "Search Tag",
                    color = Color.White)
            },

            textStyle = TextStyle(
                color = Color.Blue
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    onClick = {
                        onTextChange("")
                      onCloseClick()

                    }
                ){
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Search Icon",
                        tint = Color.Blue)
                }
            },

            trailingIcon = {

                IconButton(
                    onClick = {
                        if(text.isNotEmpty()){
                            onTextChange("")
                        }else{
                            onCloseClick()
                        }
                    }
                ){
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Blue
                    )
                }
            },

            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),

            keyboardActions = KeyboardActions(
                onSearch = {
                    if(text.trim().isNotEmpty()){
                        onSearchClick(text)
                        focusRequester.freeFocus()
                        keyboardController?.hide()

                    }else{
                        return@KeyboardActions
                    }

                }
            ),

            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = Color.Blue
            )
        )

    }


}
