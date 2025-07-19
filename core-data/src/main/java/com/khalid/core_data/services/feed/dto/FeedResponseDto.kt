package com.khalid.core_data.services.feed.dto

import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.khalid.core.feed.model.ContentType
import com.khalid.core.feed.model.SectionLayout
import com.khalid.core_data.services.feed.adapters.ContentTypeAdapter
import com.khalid.core_data.services.feed.adapters.SectionLayoutAdapter

internal data class FeedResponseDto(
    @SerializedName("sections") val sections: List<SectionDto>,
    @SerializedName("pagination") val pagination: PaginationDto
)

internal data class PaginationDto(
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("total_pages") val totalPages: Int
)

internal data class SectionDto(
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: SectionLayoutDto,
    @SerializedName("content_type") val contentType: ContentTypeDto,
    @SerializedName("order") val order: Int,
    @SerializedName("content") val content: List<JsonObject>
)

@JsonAdapter(SectionLayoutAdapter::class)
internal enum class SectionLayoutDto {
    @SerializedName("square")
    SQUARE,

    @SerializedName("big_square")
    BIG_SQUARE,

    @SerializedName("queue")
    QUEUE,

    @SerializedName("2_lines_grid")
    TWO_LINES_GRID,
}

@JsonAdapter(ContentTypeAdapter::class)
internal enum class ContentTypeDto {
    @SerializedName("podcast")
    PODCAST,

    @SerializedName("episode")
    EPISODE,

    @SerializedName("audio_book")
    AUDIO_BOOK,

    @SerializedName("audio_article")
    AUDIO_ARTICLE,
    UNKNOWN;
}

internal fun SectionLayoutDto.toCore() = when (this) {
    SectionLayoutDto.SQUARE -> SectionLayout.SQUARE
    SectionLayoutDto.BIG_SQUARE -> SectionLayout.BIG_SQUARE
    SectionLayoutDto.QUEUE -> SectionLayout.QUEUE
    SectionLayoutDto.TWO_LINES_GRID -> SectionLayout.TWO_LINES_GRID
}

internal fun ContentTypeDto.toCore() = when (this) {
    ContentTypeDto.PODCAST -> ContentType.PODCAST
    ContentTypeDto.EPISODE -> ContentType.EPISODE
    ContentTypeDto.AUDIO_BOOK -> ContentType.AUDIO_BOOK
    ContentTypeDto.AUDIO_ARTICLE -> ContentType.AUDIO_ARTICLE
    ContentTypeDto.UNKNOWN -> ContentType.UNKNOWN
}
