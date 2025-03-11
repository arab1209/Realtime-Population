package com.example.realtimepopulation.ui.shared.detailpopulation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.realtimepopulation.R
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel
import com.example.realtimepopulation.ui.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun populationScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val detailScreenData = viewModel.detailScreenData.observeAsState()
    val dtViewModel: DetailScreenViewModel = hiltViewModel()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(modifier = Modifier.shadow(10.dp),
            title = { Text(detailScreenData.value!!.areaName + " 실시간 인구 현황", fontSize = 20.sp) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                }
            })
    }) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Text("인구 혼잡도 ", fontWeight = FontWeight.Bold)
                    Text(
                        text = detailScreenData.value?.congestionLevel ?: "",
                        color = viewModel.calcAreaColor(
                            detailScreenData.value?.congestionLevel ?: "",
                        ),
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Log.d("test", dtViewModel.analyzeImgUrl(
                        detailScreenData.value!!.congestionLevel
                    ))
                    val imageUrl = dtViewModel.analyzeImgUrl(detailScreenData.value!!.congestionLevel).trim()
                    Log.d("ImageTest", "이미지 URL: $imageUrl") // URL 확인

                    val request = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .error(R.drawable.ic_main_search)
                        .listener(
                            onStart = { Log.d("Coil", "이미지 로딩 시작") },
                            onSuccess = { _, _ -> Log.d("Coil", "이미지 로딩 성공") },
                            onError = { _, result ->
                                Log.e("Coil", "이미지 로딩 실패: ${result.throwable.message}", result.throwable)
                            }
                        )
                        .build()

                    AsyncImage(
                        model = request,
                        contentDescription = detailScreenData.value!!.areaName,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}