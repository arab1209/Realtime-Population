package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.R

@Composable
fun DetailScreenTitleBox (titleText: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titleText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff4c65a7),
        )
        Image(
            painter = painterResource(R.drawable.ic_right_button),
            contentDescription = "img",
            modifier = Modifier
                .width(35.dp)
                .height(35.dp)
        )
    }
}