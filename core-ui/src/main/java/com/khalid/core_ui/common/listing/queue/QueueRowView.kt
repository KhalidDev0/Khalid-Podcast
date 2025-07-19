package com.khalid.core_ui.common.listing.queue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.khalid.core_ui.theme.Dimensions

@Composable
fun QueueRowView(
    queueCardData: List<QueueCardUiModel>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Dimensions.L),
        contentPadding = PaddingValues(horizontal = Dimensions.L),
        modifier = modifier.padding(vertical = Dimensions.L)
    ) {
        itemsIndexed(queueCardData) { index, queueCardData ->
            QueueCard(queueCardUiModel = queueCardData)
        }
    }
}
