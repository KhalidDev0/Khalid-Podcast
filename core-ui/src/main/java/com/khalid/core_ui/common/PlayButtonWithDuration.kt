package com.khalid.core_ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.khalid.core.utils.TimeFormatter
import com.khalid.core_ui.theme.Dimensions

@Composable
fun PlayButtonWithDuration(
    duration: Long,
    modifier: Modifier = Modifier
) {
    val refinedDuration = remember(duration) {
        TimeFormatter.formatHrsMins(duration)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(Dimensions.M))
            .background(Color.DarkGray)
            .clickable {

            }
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(Dimensions.L)
        )

        Text(
            text = refinedDuration,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(end = Dimensions.S)
        )
    }
}
