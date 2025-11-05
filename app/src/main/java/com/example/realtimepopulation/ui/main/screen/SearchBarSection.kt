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

/**
 * 메인 화면 검색바 섹션 (클릭 전용)
 *
 * 실제 입력 필드가 아닌 클릭 가능한 UI입니다.
 * 탭하면 전용 검색 화면으로 이동하여 입력할 수 있습니다.
 *
 * 이렇게 구현한 이유:
 * - 메인 화면에 실제 TextField를 두면 불필요한 재구성 발생
 * - 검색은 별도 화면에서 처리하는 것이 UX상 더 명확함
 * - 키보드가 메인 화면 콘텐츠를 가리는 문제 방지
 *
 * @param navController 검색 화면으로 네비게이션하기 위한 컨트롤러
 */

@Composable
fun SearchBarSection(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SearchBarDimens.Height)
            .padding(start = AppSpacing.MediumLarge, end = AppSpacing.MediumLarge, top = AppSpacing.MediumLarge)
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
                    .size(AppSpacing.ExtraExtraExtraLarge)
                    .padding(start = AppSpacing.Medium, top = AppSpacing.Medium, bottom = AppSpacing.Medium)
            )

            Text(
                text = SearchBarDimens.PlaceholderText,
                color = AppColors.Gray,
                fontSize = AppFontSizes.BodySmall,
                modifier = Modifier.padding(start = AppSpacing.ExtraSmall)
            )
        }
    }
}