package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

@Composable
fun DetailScreenSubTitleBox(
    mainText: String,
    viewModel: MainViewModel = hiltViewModel(),
    congetionLevel: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppSpacing.Medium, horizontal = AppSpacing.Large)
            .background(Color(0xfff7f7f7))
    ) {
        Text(
            text = mainText, fontSize = AppFontSizes.BodyLarge, textDecoration = TextDecoration.Underline
        )
        Text(
            "가 ", fontSize = AppFontSizes.BodyLarge
        )
        Text(
            congetionLevel,
            color = viewModel.calcAreaColor(congetionLevel),
            fontSize = AppFontSizes.TitleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = AppSpacing.ExtraSmall)
        )
        Text(
            " 입니다", fontSize = AppFontSizes.BodyLarge
        )
    }
}