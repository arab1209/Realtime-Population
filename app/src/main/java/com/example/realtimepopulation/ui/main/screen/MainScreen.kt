package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.example.realtimepopulation.ui.util.Screen
import kotlin.math.roundToInt

/**
 * 메인 화면 - 서울 지역별 실시간 인구 현황 목록
 *
 * 주요 기능:
 * - 스크롤에 따라 축소되는 헤더 (Collapsing Toolbar)
 * - 지역 필터링 칩 (구별 선택)
 * - 인구 현황 카드 리스트
 * - 검색 기능
 *
 * @param mainViewModel 메인 화면의 비즈니스 로직 및 상태 관리
 * @param navController 화면 네비게이션 컨트롤러
 */

@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val scrollState by mainViewModel.scrollState.collectAsState()
    val selectChipData by mainViewModel.selectChipData.collectAsState()
    val selectedChip by mainViewModel.selectChip.collectAsState()
    val populationData by mainViewModel.populationData.collectAsState()
    val populationMapData by mainViewModel.populationDataMap.collectAsState()

    val topAppBarHeightPx = with(LocalDensity.current) {
        scrollState.headerHeight.roundToPx().toFloat()
    }

    val chunkedSelectChipData = remember(selectChipData) {
        selectChipData.chunked(2)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(
            rememberScrollHandler(
                onPreScroll = mainViewModel::handlePreScroll,
                onPostScroll = mainViewModel::handlePostScroll,
                topAppBarHeightPx
            )
        ),
        topBar = {
            Column(
                modifier = Modifier
                    .offset {
                        IntOffset(x = 0, y = scrollState.headerOffset.roundToInt())
                    }
                    .background(AppColors.White)
                    .height(scrollState.headerHeight)
            ) {
                TitleSection()
                SearchBarSection(navController)
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding()
            ),
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.White)
        ) {
            item {
                ChipSection(
                    selectedChip = selectedChip,
                    onChipSelected = mainViewModel::setSelectChip
                )
            }

            items(
                items = chunkedSelectChipData,
                key = { pair -> pair.firstOrNull()?.hashCode() ?: 0 }
            ) { locationDataPair ->
                CardViewSection(
                    locationDataPair = locationDataPair,
                    populationDataMap = populationMapData,
                    calcAreaColor = mainViewModel::calcAreaColor,
                    onCardClick = { areaName ->
                        mainViewModel.setDetailScreenData(populationData, areaName)
                        navController.navigate(Screen.Detail.route)
                    }
                )
            }
        }
    }
}