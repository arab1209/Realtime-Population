package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleSection() {
    Box(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {// 상단 나라, 지역 이름
        Column() {
            Text(
                fontSize = 10.sp, color = Color.Black, text = "Korea"
            )
            Text(
                fontSize = 32.sp, color = Color.Black, text = "Seoul"
            )
        }
    }
}