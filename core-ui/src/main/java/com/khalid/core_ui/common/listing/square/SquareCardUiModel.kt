package com.khalid.core_ui.common.listing.square

data class SquareCardUiModel(
    val title: String,
    val imageUrl: String,
    val duration: Long,
    val progress: Float? = null,
    val date: String? = null,
)