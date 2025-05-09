package com.example.realtimepopulation.ui.shared.detailtemp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TempInfoItem(
    iconUrl: String,
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    isBold: Boolean = false,
    color: Color = Color.Unspecified
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = iconUrl,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = color
        )
    }
}