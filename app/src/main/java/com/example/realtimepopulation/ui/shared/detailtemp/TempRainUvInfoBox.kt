package com.example.realtimepopulation.ui.shared.detailtemp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.theme.AppSpacing

/**
 * 강수량 또는 자외선 지수 정보를 표시하는 박스 컴포넌트
 *
 * 아이콘, 레이블, 데이터 값, 안내 메시지를 수직으로 배치하여
 * 날씨 관련 정보를 시각적으로 제공합니다.
 *
 *
 * @param labelText 정보 레이블 (예: "강수량", "자외선지수")
 * @param dataText 측정 데이터 값 (예: "0mm", "높음")
 * @param url 표시할 아이콘 이미지 URL
 * @param msg 사용자 안내 메시지 (예: "외출 시 우산을 준비하세요")
 * @param modifier 컴포넌트에 적용할 Modifier (주로 weight로 너비 조정)
 * @param getColor 데이터 값에 따른 색상을 계산하는 함수 (위험도 시각화)
 */

@Composable
fun TempRainUvInfoBox(
    labelText: String,
    dataText: String,
    url: String,
    msg: String,
    modifier: Modifier,
    getColor: (String) -> Color
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppSpacing.Medium, vertical = AppSpacing.Medium),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                model = url,
                contentDescription = "아이콘"
            )

            Text(text = labelText)
            Text(text = dataText, color = getColor(dataText))  // 콜백 사용
            Text(text = msg, modifier = Modifier.padding(top = AppSpacing.Medium))
        }
    }
}