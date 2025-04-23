package com.example.realtimepopulation.ui.shared

import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDivider(thickness: Dp) {
    Divider(
        color = Color(0xffe7e8ee), thickness = thickness
    )
}