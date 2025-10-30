package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppCornerRadius
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing
import com.example.realtimepopulation.ui.theme.CardViewDimens
import com.example.realtimepopulation.ui.util.Screen

@Composable
fun CustomCardView(
    locationData: LocationData,
    congestionLevel: String?,
    congestionColor: Color,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit
) {
    Box(
        modifier = modifier.padding(
            horizontal = AppSpacing.Medium,
            vertical = AppSpacing.Medium
        )
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(CardViewDimens.Elevation),
            colors = CardDefaults.cardColors(containerColor = AppColors.LightBlue)
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(CardViewDimens.Weight)
                    .fillMaxWidth()
                    .padding(AppSpacing.ExtraSmall)
                    .clip(RoundedCornerShape(AppCornerRadius.Small))
                    .clickable { onCardClick() },
                model = ImageRequest.Builder(LocalContext.current)
                    .data(locationData.imgURL)
                    .crossfade(true)
                    .build(),
                contentDescription = locationData.areaName,
                contentScale = ContentScale.Crop,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppSpacing.Small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(CardViewDimens.Weight)
                ) {
                    Text(
                        text = locationData.category,
                        fontSize = AppFontSizes.LabelSmall,
                        lineHeight = AppFontSizes.LabelSmall,
                    )

                    Text(
                        text = locationData.areaName,
                        fontSize = AppFontSizes.LabelSmall,
                        lineHeight = AppFontSizes.LabelSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            bottom = AppSpacing.ExtraSmall,
                            top = AppSpacing.ExtraExtraSmall
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(AppSpacing.Small)
                        .size(AppSpacing.Medium)
                        .background(
                            color = if (congestionLevel != null) congestionColor else Color.Transparent,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}