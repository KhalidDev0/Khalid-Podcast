package com.khalid.core_ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.khalid.core_ui.theme.Dimensions

@Composable
fun CircularButton(
    modifier: Modifier = Modifier,
    iconImageVector: ImageVector,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick::invoke,
        modifier = modifier.size(Dimensions.XXXL),
        colors = IconButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.White,
        )
    ) {
        Icon(
            imageVector = iconImageVector,
            contentDescription = null,
            modifier = Modifier.size(Dimensions.L)
        )
    }

}