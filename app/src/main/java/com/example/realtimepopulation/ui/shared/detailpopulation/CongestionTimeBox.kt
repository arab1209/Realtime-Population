package com.example.realtimepopulation.ui.shared.detailpopulation

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel

@Composable
fun CongestionTimeBox(
    subTitleText: String,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    detailScreenData: MapData,
    flag: Int,
) {
    val congestIconUrl = viewModel.congestIconUrl.collectAsState()

    with(viewModel) {
        analyzeCongestIconUrl(flag)
        analyzeMaxMinPopulation(detailScreenData.forecasts)
    }

    Text(
        text = subTitleText, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        AsyncImage(
            model = congestIconUrl, contentDescription = "시간 아이콘"
        )
    }
}