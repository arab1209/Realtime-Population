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
            .padding(top = 15.dp)
            .background(Color(0xfff7f7f7))
    ) {
        Text(
            text = mainText, fontSize = 16.sp, textDecoration = TextDecoration.Underline
        )
        Text(
            "가 ", fontSize = 16.sp
        )
        Text(
            congetionLevel,
            color = viewModel.calcAreaColor(congetionLevel),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Text(
            " 입니다", fontSize = 16.sp
        )
    }
}