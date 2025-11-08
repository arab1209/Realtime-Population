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
import com.example.realtimepopulation.ui.theme.AppSpacing

/**
 * 개별 대기질 항목을 카드 형태로 표시하는 컴포넌트
 *
 * 오존, 이산화질소, 일산화탄소, 아황산가스 등의 대기질 측정값을
 * 정사각형 카드 형태로 일관되게 표시합니다.
 *
 * 사용 예시:
 * - 오존농도: 0.023ppm 좋음
 * - 이산화질소: 0.015ppm 좋음
 * - 일산화탄소: 0.4ppm 좋음
 * - 아황산가스: 0.003ppm 좋음
 *
 * @param modifier 카드에 적용할 Modifier (주로 weight로 너비 조정)
 * @param label 대기질 항목명 (예: "오존농도", "이산화질소")
 * @param value 측정된 농도 값 (ppm 단위로 표시됨)
 */

@Composable
fun AirMsgCenterDividerRow(
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit,
    dividerHeight: Dp = AppSpacing.ExtraLarge,
    padding: PaddingValues = PaddingValues(vertical = AppSpacing.Small),
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .height(dividerHeight)
                .width(1.dp)
                .align(Alignment.Center)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
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
                    Row() {
                        leftContent()
                    }
                }
                Spacer(modifier = Modifier.width(AppSpacing.ExtraLarge)) // 구분선까지의 간격
            }

            // 간격 유지를 위한 투명한 spacer (구분선 영역)
            Spacer(modifier = Modifier.width(1.dp))

            // 오른쪽 부분
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(AppSpacing.ExtraLarge)) // 구분선부터의 간격
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    Row() {
                        rightContent()
                    }
                }
            }
        }
    }
}