package com.example.realtimepopulation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

/**
 * Collapsing Toolbar(축소 가능한 헤더)를 구현하기 위한 NestedScroll 핸들러
 *
 * 동작 원리:
 * 1. 사용자가 위로 스크롤 → onPreScroll 호출 → 헤더 숨김
 * 2. 사용자가 아래로 스크롤 → onPostScroll 호출 → 헤더 표시
 * 3. ViewModel에서 headerOffset을 업데이트하여 헤더 위치 조정
 *
 * 예시:
 * - 위로 스크롤: headerOffset = -topAppBarHeightPx (완전히 숨김)
 * - 아래로 스크롤: headerOffset = 0 (완전히 표시)
 *
 * @param onPreScroll 스크롤 전처리 콜백 (위로 스크롤 시 호출)
 *                    파라미터: (scrollDelta: Float, topAppBarHeightPx: Float)
 * @param onPostScroll 스크롤 후처리 콜백 (아래로 스크롤 시 호출)
 *                     파라미터: (scrollDelta: Float, topAppBarHeightPx: Float)
 * @param topAppBarHeightPx TopAppBar의 픽셀 높이 (헤더가 숨겨질 수 있는 최대 거리)
 * @return 메모이제이션된 NestedScrollConnection 객체
 */

@Composable
fun rememberScrollHandler(
    onPreScroll: (Float, Float) -> Unit,
    onPostScroll: (Float, Float) -> Unit,
    topAppBarHeightPx: Float
): NestedScrollConnection {
    return remember(onPreScroll, onPostScroll, topAppBarHeightPx) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                onPreScroll(available.y, topAppBarHeightPx)
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                onPostScroll(available.y, topAppBarHeightPx)
                return Offset.Zero
            }
        }
    }
}