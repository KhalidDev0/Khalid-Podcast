package com.khalid.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.khalid.core_ui.bases.LocalNavController
import com.khalid.core_ui.common.CircularButton
import com.khalid.core_ui.destinations.RootDestination
import com.khalid.core_ui.theme.Dimensions
import com.khalid.core_ui.utils.navigateSingleTop

@Composable
fun FeedTopBar(
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(all = Dimensions.M)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(Dimensions.XXXL)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
            )
        }
        Text(
            "Hi Khalid",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = Dimensions.M)
                .weight(1f)
        )
        CircularButton(
            iconImageVector = Icons.Default.Search,
            onClick = {
                navController.navigateSingleTop(RootDestination.SearchScreen)
            }
        )
    }
}