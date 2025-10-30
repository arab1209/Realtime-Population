package com.example.realtimepopulation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

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