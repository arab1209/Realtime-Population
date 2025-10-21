package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.ui.main.rememberScrollHandler
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.theme.AppColors
import kotlin.math.roundToInt

@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val scrollState by mainViewModel.scrollState.collectAsState()
    val selectChipData by mainViewModel.selectChipData.collectAsState()

    val topAppBarHeightPx = with(LocalDensity.current) {
        scrollState.headerHeight.roundToPx().toFloat()
    }

    Scaffold(
        modifier = Modifier.nestedScroll(rememberScrollHandler(mainViewModel, topAppBarHeightPx)),
        topBar = {
            Column(modifier = Modifier
                .offset {
                    IntOffset(x = 0, y = scrollState.headerOffset.roundToInt())
                }
                .background(AppColors.White)
                .height(scrollState.headerHeight)) {
                TitleSection()
                SearchBarSection(navController)
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding()
            ), modifier = Modifier
                .fillMaxSize()
                .background(AppColors.White)
        ) {
            item {
                ChipSection(mainViewModel)
            }
            items(count = selectChipData.chunked(2).size) { index ->
                CardViewSection(selectChipData, index, navController, mainViewModel)
            }
        }
    }
}