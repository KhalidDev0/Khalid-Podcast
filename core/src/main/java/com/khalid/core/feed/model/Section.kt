package com.khalid.core.feed.model

data class Section<T : Media>(
    val name: String,
    val layout: SectionLayout,
    val contentType: ContentType,
    val order: Int,
    val content: List<T>
)

enum class SectionLayout {
    SQUARE,
    BIG_SQUARE,
    QUEUE,
    TWO_LINES_GRID,
}

enum class ContentType {
    PODCAST,
    EPISODE,
    AUDIO_BOOK,
    AUDIO_ARTICLE,
    UNKNOWN
}
