package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

@Composable
fun LegendItem(
    color: Color,
    label: String,
    percentage: Float,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(AppSpacing.Large)
                    .background(color = color, shape = MaterialTheme.shapes.small)
            )
            Text(
                text = label,
                fontSize = AppFontSizes.BodySmall,
                color = Color.Black
            )
        }
        Text(
            text = String.format("%.1f%%", percentage), // 퍼센트 표시
            fontSize = AppFontSizes.BodySmall,
            color = AppColors.Gray
        )
    }
}
