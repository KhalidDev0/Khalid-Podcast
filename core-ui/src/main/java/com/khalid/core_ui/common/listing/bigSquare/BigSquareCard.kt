package com.khalid.core_ui.common.listing.bigSquare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.khalid.core_ui.common.RemoteImageLoader
import com.khalid.core_ui.theme.Dimensions

@Composable
fun BigSquareCard(
    bigSquareCardUiModel: BigSquareCardUiModel,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier
            .height(180.dp)
            .width(230.dp)
            .clip(RoundedCornerShape(Dimensions.M))
    ) {
        RemoteImageLoader(
            url = bigSquareCardUiModel.imageUrl,
            contentDescription = bigSquareCardUiModel.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = .05F),
                            Color.Black.copy(alpha = .50F),
                        )
                    )
                )
        )

        Column(
            modifier = Modifier.padding(all = Dimensions.S)
        ) {
            Text(
                bigSquareCardUiModel.title,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = Dimensions.XXS)
            )

            if (bigSquareCardUiModel.totalEpisodes > 0) {
                Text(
                    text = "${bigSquareCardUiModel.totalEpisodes} Episodes",
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}