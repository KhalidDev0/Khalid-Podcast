package com.khalid.core_ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.khalid.core.utils.TimeFormatter

@Composable
fun DaysPassedText(
    date: String,
    modifier: Modifier = Modifier
) {
    val refinedDate = remember(date) { TimeFormatter.formatRelativeDay(date) }

    Text(
        text = refinedDate,
        color = Color.DarkGray,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier
    )
}