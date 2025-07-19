package com.khalid.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.khalid.core_ui.theme.Dimensions
import com.khalid.search.presentation.SearchEvent

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    value: String,
    enabled: Boolean,
    onEvent: (SearchEvent) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(all = Dimensions.M)
    ) {
        OutlinedTextField(
            value = value,
            enabled = enabled,
            singleLine = true,
            onValueChange = { onEvent(SearchEvent.OnTextChanged(it)) },
            label = { Text("Search") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White,
            ),
            textStyle = MaterialTheme.typography.titleMedium.copy(color = Color.White),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.White,
                )
            },
            modifier = Modifier.weight(1f)
        )

    }
}