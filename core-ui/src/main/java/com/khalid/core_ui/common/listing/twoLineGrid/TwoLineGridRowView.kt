package com.khalid.core_ui.common.listing.twoLineGrid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khalid.core_ui.theme.Dimensions

@Composable
fun TwoLineGridRowView(
    modifier: Modifier = Modifier,
    twoLineGridCardData: List<TwoLineGridCardUiModel>,
    gridRows: Int = 2,
) {
    val gridState = rememberLazyGridState()

    LazyHorizontalGrid(
        rows = GridCells.Fixed(gridRows),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.L),
        verticalArrangement = Arrangement.spacedBy(Dimensions.M),
        contentPadding = PaddingValues(horizontal = Dimensions.L),
        modifier = modifier
            .height(80.dp * gridRows + Dimensions.M)
            .padding(vertical = Dimensions.L)
    ) {
        itemsIndexed(twoLineGridCardData) { _, card ->
            TwoLineGridCard(twoLineGridCardUiModel = card)
        }
    }
}

