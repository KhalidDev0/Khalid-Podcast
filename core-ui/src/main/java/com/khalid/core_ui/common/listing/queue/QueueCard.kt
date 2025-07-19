package com.khalid.core_ui.common.listing.queue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
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
import com.khalid.core_ui.common.CircularButton
import com.khalid.core_ui.common.RemoteImageLoader
import com.khalid.core_ui.theme.Dimensions

@Composable
fun QueueCard(
    queueCardUiModel: QueueCardUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.S),
        modifier = modifier
            .height(140.dp)
            .width(300.dp)
            .clip(RoundedCornerShape(Dimensions.M))
            .background(Color.DarkGray)
            .padding(all = Dimensions.M)
    ) {
        RemoteImageLoader(
            url = queueCardUiModel.imageUrl,
            contentDescription = queueCardUiModel.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .clip(RoundedCornerShape(Dimensions.M))
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                queueCardUiModel.title,
                color = Color.White,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = Dimensions.XS)
            )

            Text(
                text = "${queueCardUiModel.totalEpisodes} Episodes",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }

        CircularButton(
            iconImageVector = Icons.Default.PlayArrow,
            onClick = {}
        )
    }
}
