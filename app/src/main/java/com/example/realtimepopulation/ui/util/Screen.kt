package com.example.realtimepopulation.ui.util

import com.example.realtimepopulation.R

sealed class Screen (
    val route: String,
    val title: String,
    val image: Int,
    val image2: Int
){
    object Home : Screen("home", "홈", R.drawable.ic_bottom_nav_home_select, R.drawable.ic_bottom_nav_home_unselect)
    object Map : Screen("map", "지도", R.drawable.ic_bottom_nav_map_select, R.drawable.ic_bottom_nav_map_unselect)
}