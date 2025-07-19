package com.khalid.core_data.services.search.dto

import com.google.gson.annotations.SerializedName
import com.khalid.core.search.model.SearchContent
import com.khalid.core.search.model.SearchResponse
import com.khalid.core.search.model.SearchSection

data class SearchResponseDto(

    @field:SerializedName("sections")
    val sections: List<SectionsItemDto?>? = null
)

data class ContentItemDto(

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("popularityScore")
    val popularityScore: String? = null,

    @field:SerializedName("score")
    val score: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("episode_count")
    val episodeCount: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("podcast_id")
    val podcastId: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("priority")
    val priority: String? = null
)

data class SectionsItemDto(

    @field:SerializedName("content_type")
    val contentType: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("content")
    val content: List<ContentItemDto?>? = null,

    @field:SerializedName("order")
    val order: String? = null
)


fun SearchResponseDto.toDomain() = SearchResponse(
    sections = sections?.map { it?.toDomain() }
)

fun SectionsItemDto.toDomain() = SearchSection(
    name = name,
    content = content?.map { it?.toDomain() },
    order = order
)

fun ContentItemDto.toDomain() = SearchContent(
    duration = duration,
    avatarUrl = avatarUrl,
    name = name
)
