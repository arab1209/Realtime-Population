package com.example.realtimepopulation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.realtimepopulation.ui.theme.SemanticColors
import com.example.realtimepopulation.ui.util.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(5000)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SemanticColors.Primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "앱 이름",
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }

}