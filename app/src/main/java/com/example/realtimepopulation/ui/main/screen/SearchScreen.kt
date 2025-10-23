package com.example.realtimepopulation.ui.main.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.realtimepopulation.ui.shared.CustomCardView
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppCornerRadius
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing
import com.example.realtimepopulation.ui.theme.CardViewDimens
import com.example.realtimepopulation.ui.theme.SearchBarDimens
import com.example.realtimepopulation.ui.util.Screen

@Composable
fun SearchScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val savedSearchQuery by viewModel.savedSearchQuery.collectAsState()
    val searchList by viewModel.searchList.collectAsState()

    savedSearchQuery.takeIf { it.isNotBlank() }?.let {
        viewModel.searchLocationData(it)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(SearchBarDimens.Height)
            .padding(start = AppSpacing.MediumLarge, end = AppSpacing.MediumLarge, top = AppSpacing.MediumLarge)
            .clip(RoundedCornerShape(AppCornerRadius.Large))
            .background(AppColors.LightBlue)) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_main_search),
                    contentDescription = SearchBarDimens.PlaceholderText,
                    modifier = Modifier
                        .size(AppSpacing.ExtraExtraExtraLarge)
                        .padding(start = AppSpacing.Medium, top = AppSpacing.Small, bottom = AppSpacing.Small)
                )

                BasicTextField(textStyle = TextStyle(
                    color = AppColors.Black, fontSize = AppFontSizes.LabelSmall, textDecoration = TextDecoration.None
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
                        .padding(start = AppSpacing.ExtraSmall)
                        .border(width = 0.dp, color = Color.Transparent),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = SearchBarDimens.PlaceholderText, color = AppColors.Gray, fontSize = AppFontSizes.LabelSmall
                            )
                        }
                        innerTextField()
                    })
            }
        }

        LazyColumn() {
            items(count = searchList.chunked(2).size) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                ) {
                    searchList.chunked(2)[index].forEach {
                        CustomCardView(
                            viewModel,
                            it,
                            modifier = Modifier
                                .weight(CardViewDimens.Weight)
                                .aspectRatio(CardViewDimens.AspectRatio)
                                .fillMaxHeight(),
                            navController
                        )
                    }
                }
            }
        }
    }
}