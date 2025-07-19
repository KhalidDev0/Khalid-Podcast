package com.khalid.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.khalid.core_ui.bases.BaseScreen
import com.khalid.core_ui.common.listing.twoLineGrid.TwoLineGridCard
import com.khalid.core_ui.theme.Dimensions
import com.khalid.search.components.SearchTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseScreen(
        topBar = {
            SearchTopBar(
                value = state.searchValue,
                enabled = true,
                onEvent = onEvent
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                Dimensions.L,
                if (state.twoLineGridCardUiModels.isEmpty() || state.isSearching) Alignment.CenterVertically else Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
                .padding(horizontal = Dimensions.M)
        ) {
            if (state.isSearching) {
                item {
                    CircularProgressIndicator(color = Color.White)
                }
            } else if (state.twoLineGridCardUiModels.isEmpty()) {
                item {
                    Text(
                        "No content to show",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                    )
                }
            }

            if (state.isSearching.not()) {
                itemsIndexed(state.twoLineGridCardUiModels) { _, twoLineGridCardUiModels ->
                    TwoLineGridCard(
                        twoLineGridCardUiModel = twoLineGridCardUiModels,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimensions.XXXXXXL)
                    )
                }
            }
        }
    }
}
