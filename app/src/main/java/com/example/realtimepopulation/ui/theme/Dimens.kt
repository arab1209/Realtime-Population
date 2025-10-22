package com.example.realtimepopulation.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppFontSizes {
    val ExtraSmall: TextUnit = 8.sp
    val Small: TextUnit = 10.sp
    val Title: TextUnit = 32.sp
}

object AppSpacing {
    val XSmall: Dp = 5.dp
    val MediumSmall: Dp = 6.dp
    val Small: Dp = 8.dp
    val Medium: Dp = 10.dp
    val MediumLarge: Dp = 12.dp
    val Large: Dp = 16.dp
    val XLarge: Dp = 20.dp
    val XXLarge: Dp = 40.dp
}

object MapDimens {
    val CardPositionX = 100.dp
    val CardPositionY = 120.dp
    val CardSizeX = 300.dp
    val CardSizeY = 250.dp
    const val CardMarkerSizeX = 25
    const val CardMarkerSizeY = 35
}

object AppCornerRadius {
    val Small: Dp = 6.dp
    val Medium: Dp = 16.dp
    val Large: Dp = 40.dp
}

// 섹션별 고유 값만 정의
object TitleSectionDimens {
    const val CountryName = "Korea"
    const val RegionName = "Seoul"
}

object SearchBarDimens {
    val Height: Dp = 70.dp
    const val IconDescription = "ic_main_search"
    const val PlaceholderText = "무엇을 하고 놀까요?"
}

object CardViewDimens {
    const val Weight = 1f
    const val AspectRatio = 1f
    val Elevation: Dp = 6.dp
}