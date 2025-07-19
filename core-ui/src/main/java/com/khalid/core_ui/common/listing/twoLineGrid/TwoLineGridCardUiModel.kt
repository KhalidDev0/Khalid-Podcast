package com.khalid.core_ui.common.listing.twoLineGrid

data class TwoLineGridCardUiModel(
    val title: String,
    val imageUrl: String,
    val duration: Long? = null,
    val progress: Float? = null,
    val date: String? = null,
)