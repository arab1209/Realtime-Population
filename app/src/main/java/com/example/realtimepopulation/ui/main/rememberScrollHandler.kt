package com.example.realtimepopulation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

@Composable
fun rememberScrollHandler(
    viewModel: MainViewModel,
    topAppBarHeightPx: Float
): NestedScrollConnection {
    return remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                viewModel.handlePreScroll(
                    delta = available.y,
                    maxHeight = topAppBarHeightPx
                )
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                viewModel.handlePostScroll(
                    delta = available.y,
                    maxHeight = topAppBarHeightPx
                )
                return Offset.Zero
            }
        }
    }
}