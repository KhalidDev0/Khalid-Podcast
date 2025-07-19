package com.khalid.core_ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * A drop-in replacement for [LazyColumn] that emits [onLoadMore] when the
 * user scrolls near the bottom.  Suitable for any paged list.
 *
 * @param isLoadingTail  `true` when the *next page* is currently loading
 *                       (shows a spinner as the last item).
 * @param onLoadMore     Called **once** when the user scrolls within
 *                       [loadMoreThreshold] items of the end.
 * @param loadMoreThreshold  How many items from the end should trigger loading.
 * @param listState      Provide your own to preserve position across recomposition /
 *                       navigation; otherwise a state is remembered internally.
 * @param contentPadding Forwarded to LazyColumn.contentPadding.
 * @param modifier       Usual modifier chain.
 * @param content        Your list items – use the receiver just like a normal
 *                       LazyColumnScope.
 */
@Composable
fun InfiniteLazyColumn(
    modifier: Modifier = Modifier,
    isLoadingTail: Boolean,
    onLoadMore: () -> Unit,
    loadMoreThreshold: Int = 5,
    listState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: LazyListScope.() -> Unit
) {
    LaunchedEffect(listState) {
        snapshotFlow {
            val info = listState.layoutInfo
            if (info.totalItemsCount == 0) return@snapshotFlow false
            val lastVisible = info.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisible >= info.totalItemsCount - loadMoreThreshold
        }
            .distinctUntilChanged()
            .collect { nearEnd ->
                if (nearEnd && !isLoadingTail) onLoadMore()
            }
    }

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState,
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        content(this)

        if (isLoadingTail) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator(color = Color.White) }
            }
        }
    }
}
