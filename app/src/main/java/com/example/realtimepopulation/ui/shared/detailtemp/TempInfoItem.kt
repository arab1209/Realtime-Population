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
import com.example.realtimepopulation.ui.theme.AppSpacing

/**
 * 아이콘과 텍스트를 수평으로 배치하여 날씨 정보를 표시하는 컴포넌트
 *
 * 날씨 관련 아이콘과 함께 측정값이나 상태를 표시합니다.
 *
 * 사용 예시:
 * - [온도계 아이콘] 25°C
 * - [습도 아이콘] 습도 65%
 * - [바람 아이콘] 바람 3.5m/s
 *
 * @param iconUrl 표시할 아이콘 이미지 URL
 * @param text 아이콘 옆에 표시할 텍스트 (측정값 또는 설명)
 * @param fontSize 텍스트의 폰트 크기 (기본값: 미지정)
 * @param isBold 텍스트를 굵게 표시할지 여부 (기본값: false)
 * @param color 텍스트 색상 (기본값: 미지정, 주로 강조용으로 사용)
 */

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
            modifier = Modifier.size(AppSpacing.ExtraLarge)
        )
        Spacer(modifier = Modifier.width(AppSpacing.Small))
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = color
        )
    }
}