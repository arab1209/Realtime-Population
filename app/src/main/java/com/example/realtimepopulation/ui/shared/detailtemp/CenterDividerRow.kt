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
 * 중앙에 수직 구분선을 두고 좌우로 컨텐츠를 배치하는 레이아웃 컴포넌트
 *
 * 두 개의 관련된 정보를 시각적으로 구분하여 표시할 때 사용합니다.
 * 좌우 컨텐츠는 각각 중앙 정렬되며, 구분선과의 간격이 동일하게 유지됩니다.
 *
 * 사용 예시:
 * - 실제 온도 │ 체감 온도
 * - 습도 │ 바람
 * - 최저기온/최고기온 │ 일출/일몰
 * - 미세먼지 │ 초미세먼지
 *
 * @param leftContent 왼쪽에 표시할 컴포저블 컨텐츠
 * @param rightContent 오른쪽에 표시할 컴포저블 컨텐츠
 * @param dividerHeight 중앙 구분선의 높이 (기본값: AppSpacing.ExtraLarge)
 * @param padding 전체 Row의 상하좌우 패딩 (기본값: 상하 Small)
 */

@Composable
fun CenterDividerRow(
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit,
    dividerHeight: Dp = AppSpacing.ExtraLarge,
    padding: PaddingValues = PaddingValues(vertical = AppSpacing.Small),
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // 중앙 수직선
        Divider(
            color = Color.LightGray,
            modifier = Modifier.height(dividerHeight).width(1.dp).align(Alignment.Center)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(padding),
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
                    leftContent()
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
                    rightContent()
                }
            }
        }
    }
}