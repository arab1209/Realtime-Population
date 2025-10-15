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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

@Composable
fun TempRainUvInfoBox(
    labelText: String,
    dataText: String,
    url: String,
    msg: String,
    modifier: Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
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
            Text(text = dataText, color = viewModel.calcAreaColor(dataText))
            Text(text = msg, modifier = Modifier.padding(top = 10.dp))

        }
    }
}