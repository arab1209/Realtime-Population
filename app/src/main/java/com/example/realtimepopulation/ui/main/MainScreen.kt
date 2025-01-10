package com.example.realtimepopulation.ui.main

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.realtimepopulation.data.main.LocationData
import com.example.realtimepopulation.ui.base.main.CardViewSection
import com.example.realtimepopulation.ui.base.main.ChipSection
import com.example.realtimepopulation.ui.base.main.SearchBarSection
import com.example.realtimepopulation.ui.base.main.TitleSection

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val (seoulLocationData, viewModel) = getSeoulAreaNames(context)
    val selectChip by viewModel.selectChip.collectAsState()

    viewModel.getAreaPopulationData()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
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

            seoulLocationData?.filter { it.category == selectChip }?.chunked(2)
                ?.forEach { location ->
                    item {
                        CardViewSection(location)
                    }
                }

        }
    }/*val navController = rememberNavController()
    NavGraph(navController)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Black)
            ) { }
            Box(modifier = Modifier.fillMaxSize()) {
                NaverMap() {
                    seoulLocationData?.forEach {
                        Marker(
                            state = MarkerState(position = LatLng(it.lat, it.long)),
                            captionText = it.areaName,
                            width = 20.dp,
                            height = 30.dp
                        )
                    }
                }
            }
        }
    }*/
}

@Composable
fun getSeoulAreaNames(context: Context): Pair<List<LocationData>?, MainViewModel> {
    val viewModel: MainViewModel = viewModel()
    viewModel.readSeoulAreasFromExcel(context)
    val seoulLocationData by viewModel.seoulLocationData.collectAsState()
    return Pair(seoulLocationData, viewModel)
}