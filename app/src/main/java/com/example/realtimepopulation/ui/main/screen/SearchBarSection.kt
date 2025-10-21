package com.example.realtimepopulation.ui.main.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.realtimepopulation.R
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppCornerRadius
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing
import com.example.realtimepopulation.ui.theme.SearchBarDimens
import com.example.realtimepopulation.ui.theme.TitleSectionDimens
import com.example.realtimepopulation.ui.util.Screen

@Composable
fun SearchBarSection(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SearchBarDimens.Height)
            .padding(start = AppSpacing.XLarge, end = AppSpacing.XLarge, top = AppSpacing.XLarge)
            .clip(RoundedCornerShape(AppCornerRadius.Large))
            .background(AppColors.LightBlue)
            .clickable {
                navController.navigate(Screen.Search.route)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_main_search),
                contentDescription = SearchBarDimens.PlaceholderText,
                modifier = Modifier
                    .size(AppSpacing.XXLarge)
                    .padding(start = AppSpacing.MediumLarge, top = AppSpacing.Medium, bottom = AppSpacing.Medium)
            )

            Text(
                text = SearchBarDimens.PlaceholderText,
                color = AppColors.Gray,
                fontSize = AppFontSizes.Small,
                modifier = Modifier.padding(start = AppSpacing.XSmall)
            )
        }
    }
}