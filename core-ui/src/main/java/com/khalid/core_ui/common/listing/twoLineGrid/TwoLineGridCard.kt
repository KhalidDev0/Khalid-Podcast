package com.khalid.core_ui.common.listing.twoLineGrid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun TwoLineGridCard(
    twoLineGridCardUiModel: TwoLineGridCardUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.S),
        modifier = modifier.width(300.dp)
    ) {
        RemoteImageLoader(
            url = twoLineGridCardUiModel.imageUrl,
            contentDescription = twoLineGridCardUiModel.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight()
                .size(60.dp)
                .clip(RoundedCornerShape(Dimensions.M))
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            DaysPassedText(
                date = twoLineGridCardUiModel.date.toString()
            )

            Text(
                twoLineGridCardUiModel.title,
                color = Color.White,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = Dimensions.XS)
            )

            twoLineGridCardUiModel.duration?.let {
                PlayButtonWithDuration(
                    duration = it
                )
            }
        }
    }
}