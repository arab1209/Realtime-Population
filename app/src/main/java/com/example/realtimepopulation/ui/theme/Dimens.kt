package com.example.realtimepopulation.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppFontSizes {
    val LabelSmall: TextUnit = 11.sp
    val LabelMedium: TextUnit = 12.sp
    val LabelLarge: TextUnit = 14.sp

    val BodySmall: TextUnit = 12.sp
    val BodyMedium: TextUnit = 14.sp
    val BodyLarge: TextUnit = 16.sp

    val TitleSmall: TextUnit = 14.sp
    val TitleMedium: TextUnit = 16.sp
    val TitleLarge: TextUnit = 22.sp

    val HeadlineSmall: TextUnit = 24.sp
    val HeadlineMedium: TextUnit = 28.sp
    val HeadlineLarge: TextUnit = 32.sp

    val DisplaySmall: TextUnit = 36.sp
    val DisplayMedium: TextUnit = 45.sp
    val DisplayLarge: TextUnit = 57.sp
}

object AppSpacing {
    // Material 3 spacing scale (4dp 기반)
    val None: Dp = 0.dp
    val ExtraSmall: Dp = 4.dp      // 기존 XSmall 대체
    val Small: Dp = 8.dp
    val Medium: Dp = 12.dp         // 기존 MediumLarge 대체
    val Large: Dp = 16.dp
    val MediumLarge: Dp = 20.dp
    val ExtraLarge: Dp = 24.dp     // 기존 XLarge 대체
    val ExtraExtraLarge: Dp = 32.dp
    val ExtraExtraExtraLarge: Dp = 40.dp // 기존 XXLarge 대체
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

object CardViewDimens {
    const val Weight = 1f
    const val AspectRatio = 1f
    val Elevation: Dp = 6.dp
}