package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.core_ui.LocalSpacing
import com.example.tracker_domain.model.TrackedFood
import com.example.core.R
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.use_case.TrackFood
import com.example.tracker_presentation.components.NutrientInfo
import java.time.LocalDate

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
){
    val spacing = LocalSpacing.current
    Row(modifier = modifier
        .clip(RoundedCornerShape(5.dp))
        .padding(spacing.spaceExtraSmall)
        .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
        .background(MaterialTheme.colors.surface)
        .padding(end = spacing.spaceMedium)
        .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = trackedFood.imageUrl,
                builder = {
                    crossfade(true)
                    error(R.drawable.ic_burger)
                    fallback(R.drawable.ic_burger)
                }
            ),
            contentDescription = trackedFood.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        bottomStart = 5.dp
                    )
                )
        )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Row(modifier = Modifier.weight(1f)) {
            Column (
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 5.dp)
            ) {
                Text(
                    text = trackedFood.name,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                Text(
                    text = stringResource(
                        id = R.string.nutrient_info,
                        trackedFood.amount,
                        trackedFood.calories
                    ),
                    style = MaterialTheme.typography.body2
                )
            }
            Column(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.Bottom
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.delete),
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onDeleteClick() }
                )
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NutrientInfo(
                        name = stringResource(id = R.string.carbs),
                        amount = trackedFood.carbs,
                        unit = stringResource(id = R.string.grams),
                        amountTextSize = 16.sp,
                        unitTextSize = 12.sp,
                        nameTestStyle = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    NutrientInfo(
                        name = stringResource(id = R.string.protein),
                        amount = trackedFood.protein,
                        unit = stringResource(id = R.string.grams),
                        amountTextSize = 16.sp,
                        unitTextSize = 12.sp,
                        nameTestStyle = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    NutrientInfo(
                        name = stringResource(id = R.string.fat),
                        amount = trackedFood.fat,
                        unit = stringResource(id = R.string.grams),
                        amountTextSize = 16.sp,
                        unitTextSize = 12.sp,
                        nameTestStyle = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewTrackedFoodItem() {
    val trackedFood = TrackedFood(
        name = "WaterMelon",
        carbs = 100,
        protein = 100,
        fat = 100,
        imageUrl = null,
        mealType = MealType.Snack,
        amount = 100,
        date = LocalDate.now(),
        calories = 100,
        id = 100
    )
    TrackedFoodItem(trackedFood = trackedFood, {})
}
