package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PopulationTitleBox(titleText: String) {
    Text(
        text = titleText,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xff4c65a7),
        modifier = Modifier.padding(start = 10.dp, top = 10.dp)
    )
}