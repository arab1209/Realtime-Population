package com.example.realtimepopulation.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.realtimepopulation.ui.main.MainViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    val viewModel: MainViewModel = hiltViewModel()
    val selectedIndex by viewModel.selectedIndex.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        viewModel.updateSelectedIndex(viewModel.getIndexForRoute(currentRoute))
    }

    BottomNavigation(
        modifier = Modifier.navigationBarsPadding(), backgroundColor = Color.White
    ) {

        viewModel.navItems.forEachIndexed { index, item ->
            BottomNavigationItem(icon = {
                Image(
                    modifier = Modifier
                        .width(18.dp)
                        .height(18.dp), painter = painterResource(
                        id = if (selectedIndex == index) item.image else item.image2
                    ), contentDescription = "Home"
                )

            }, selected = selectedIndex == index && currentRoute == item.route, onClick = {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it) { saveState = true }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }, label = {
                Text(
                    fontSize = 6.sp,
                    text = item.title,
                    lineHeight = 8.sp,
                    fontWeight = if (selectedIndex == 0) FontWeight.Bold else FontWeight.Normal
                )

            })
        }
    }
}