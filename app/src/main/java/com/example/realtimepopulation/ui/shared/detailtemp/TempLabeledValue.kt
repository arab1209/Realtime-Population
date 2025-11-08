package com.example.realtimepopulation.ui.shared.detailtemp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

/**
 * 레이블과 값을 수직으로 배치하여 표시하는 범용 컴포넌트
 *
 * 레이블(설명)을 위에, 값(데이터)을 아래에 배치하여
 * 날씨 정보를 깔끔하게 표현합니다.
 *
 * 사용 예시:
 * - 최저기온: -5°C
 * - 최고기온: 10°C
 * - 일출: 07:30
 * - 일몰: 17:45
 *
 * @param label 상단에 표시할 레이블 텍스트 (예: "최저기온", "일출")
 * @param value 하단에 표시할 값 (예: "-5°C", "07:30")
 * @param labelFontSize 레이블의 폰트 크기 (기본값: 미지정)
 * @param valueFontSize 값의 폰트 크기 (기본값: 미지정)
 * @param valueColor 값의 텍스트 색상 (기본값: 미지정)
 */

@Composable
fun TempLabeledValue(
    label: String,
    value: String,
    labelFontSize: TextUnit = TextUnit.Unspecified,
    valueFontSize: TextUnit = TextUnit.Unspecified,
    valueColor: Color = Color.Unspecified,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = labelFontSize)
        Text(
            text = value,
            fontSize = valueFontSize,
            fontWeight = FontWeight.Bold,
            color = valueColor
        )
    }
}