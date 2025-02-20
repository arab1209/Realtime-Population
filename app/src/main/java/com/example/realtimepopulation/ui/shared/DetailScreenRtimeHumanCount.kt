package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreenRtimeHumanCount (minCount: String?, maxCount: String?, congestList: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Column() {
            Text(
                text = "현재 실시간 인구 : $minCount ~ ${maxCount}명",
                fontSize = 16.sp,
                color = Color(0xff798fd2),
                fontWeight = FontWeight.Bold
            )
            congestList.forEach {
                Text(
                    text = it,
                    color = Color(0xff626262),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}