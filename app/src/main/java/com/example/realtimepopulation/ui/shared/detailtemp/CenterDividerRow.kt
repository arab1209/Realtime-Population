package com.example.realtimepopulation.ui.shared.detailtemp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CenterDividerRow(
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit,
    dividerHeight: Dp = 24.dp,
    padding: PaddingValues = PaddingValues(vertical = 8.dp),
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // 중앙 수직선
        Divider(
            color = Color.LightGray,
            modifier = Modifier.height(dividerHeight).width(1.dp).align(Alignment.Center)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(padding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 왼쪽 부분
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    leftContent()
                }
                Spacer(modifier = Modifier.width(24.dp)) // 구분선까지의 간격
            }

            // 간격 유지를 위한 투명한 spacer (구분선 영역)
            Spacer(modifier = Modifier.width(1.dp))

            // 오른쪽 부분
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(24.dp)) // 구분선부터의 간격
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    rightContent()
                }
            }
        }
    }
}