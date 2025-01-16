package com.example.realtimepopulation.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.R
import com.example.realtimepopulation.ui.main.MainViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier,
) {
    val viewModel: MainViewModel = hiltViewModel()
    val selectedIndex by viewModel.selectedIndex.collectAsState()

        BottomNavigation(
            modifier = modifier
                .height(50.dp)
                .navigationBarsPadding()
            ,backgroundColor = Color.White
        ) {
            BottomNavigationItem(icon = {
                Image(
                    modifier = Modifier
                        .width(18.dp)
                        .height(18.dp), painter = painterResource(
                        id = if (selectedIndex == 0) R.drawable.ic_bottom_nav_home_select
                        else R.drawable.ic_bottom_nav_home_unselect
                    ), contentDescription = "Home"
                )

            }, selected = selectedIndex == 0, onClick = { viewModel.updateSelectedIndex(0) }, label = {
                Text(
                    fontSize = 6.sp,
                    text = "홈",
                    lineHeight = 8.sp,
                    fontWeight = if (selectedIndex == 0) FontWeight.Bold else FontWeight.Normal
                )

            })
            BottomNavigationItem(icon = {
                Image(
                    modifier = Modifier
                        .width(18.dp)
                        .height(18.dp), painter = painterResource(
                        id = if (selectedIndex == 1) R.drawable.ic_bottom_nav_map_select
                        else R.drawable.ic_bottom_nav_map_unselect
                    ), contentDescription = "map"
                )

            }, selected = selectedIndex == 1, onClick = {
                viewModel.updateSelectedIndex(1) }, label = {
                Text(
                    fontSize = 6.sp,
                    text = "지도",
                    lineHeight = 8.sp,
                    fontWeight = if (selectedIndex == 1) FontWeight.Bold else FontWeight.Normal
                )
            })
        }
}