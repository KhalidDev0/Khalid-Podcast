package com.khalid.core_ui.common.listing.square

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.khalid.core_ui.common.DaysPassedText
import com.khalid.core_ui.common.PlayButtonWithDuration
import com.khalid.core_ui.common.RemoteImageLoader
import com.khalid.core_ui.theme.Dimensions

@Composable
fun SquareCard(
    squareCardUiModel: SquareCardUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(150.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.clip(RoundedCornerShape(Dimensions.M))
        ) {
            RemoteImageLoader(
                url = squareCardUiModel.imageUrl,
                contentDescription = squareCardUiModel.title,
                contentScale = ContentScale.Crop,
            )
            squareCardUiModel.progress?.let {
                Box(
                    modifier = Modifier
                        .height(Dimensions.XS)
                        .fillMaxWidth(fraction = it.toFloat())
                        .background(Color.Red)
                )
            }
        }
        Text(
            squareCardUiModel.title,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(vertical = Dimensions.XS)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayButtonWithDuration(
                duration = squareCardUiModel.duration,
                modifier = Modifier.padding(vertical = Dimensions.XXS)
            )

            squareCardUiModel.date?.let { date ->
                DaysPassedText(
                    date = date,
                    modifier = Modifier.padding(horizontal = Dimensions.XS),
                )
            }
        }
    }
}
