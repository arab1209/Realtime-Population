package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

@Composable
fun ChipSection(viewModel: MainViewModel) {
    val selectChip by viewModel.selectChip.collectAsState()

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = 20.dp, top = 10.dp, end = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            viewModel.areaTypes.forEach { area ->
                AssistChip(
                    onClick = {
                        viewModel.setSelectChip(area)
                    },
                    label = {
                        Text(
                            area, fontSize = 10.sp
                        )
                    },
                    border = BorderStroke(0.dp, Color.Transparent),
                    shape = RoundedCornerShape(16.dp),
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (selectChip == area) Color(0xFFF3F8FE) else Color.Transparent,
                        labelColor = if (selectChip == area) Color(0xFF196EEF) else Color(
                            0xFFB8B8B8
                        )
                    )
                )
            }
        }
    }
}