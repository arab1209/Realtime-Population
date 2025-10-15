package com.example.realtimepopulation.ui.main.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.R
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.util.Screen

@Composable
fun SearchScreen (viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val savedSearchQuery by viewModel.savedSearchQuery.collectAsState()
    val searchList by viewModel.searchList.collectAsState()

    if(savedSearchQuery.isNotBlank()) {
        viewModel.searchLocationData(savedSearchQuery)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(color = Color(0xFFF3F8FE))
                .clickable {
                    navController.navigate(Screen.Search.route)
                }) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_main_search),
                    contentDescription = "ic_main_search",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 12.dp, top = 10.dp, bottom = 10.dp)
                )

                BasicTextField(textStyle = TextStyle(
                    color = Color.Black, fontSize = 10.sp, textDecoration = TextDecoration.None
                ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.saveSearchQuery(searchQuery)
                    }),
                    cursorBrush = SolidColor(Color.Transparent),
                    value = searchQuery,
                    onValueChange = { viewModel.setQueryText(it) },
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .border(width = 0.dp, color = Color.Transparent),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "무엇을 하고 놀까요 ?", color = Color(0xFFB8B8B8), fontSize = 8.sp
                            )
                        }
                        innerTextField()
                    })
            }
        }

        searchList.forEach {
            Text(
                text = it.areaName
            )
        }

    }
}