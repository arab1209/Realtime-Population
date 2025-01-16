package com.example.realtimepopulation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.ui.base.main.CardViewSection
import com.example.realtimepopulation.ui.base.main.ChipSection
import com.example.realtimepopulation.ui.base.main.SearchBarSection
import com.example.realtimepopulation.ui.base.main.TitleSection

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val selectChipData by viewModel.selectChipData.collectAsState()
    val popData by viewModel.populationData.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ) {
            item {
                TitleSection()
            }

            item {
                SearchBarSection(viewModel)
            }

            item {
                ChipSection(viewModel)
            }

            items(count = selectChipData.chunked(2).size) { index ->
                CardViewSection(selectChipData, index, popData)
            }
        }
    }
}