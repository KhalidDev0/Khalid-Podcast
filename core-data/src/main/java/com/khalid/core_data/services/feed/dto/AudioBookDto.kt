package com.khalid.core_data.services.feed.dto

import com.google.gson.annotations.SerializedName
import com.khalid.core.feed.model.AudioBook

internal data class AudioBookDto(
    @SerializedName("audiobook_id") val audiobookId: String,
    @SerializedName("name") val name: String,
    @SerializedName("author_name") val authorName: String,
    @SerializedName("description") val description: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("duration") val duration: Long,
    @SerializedName("language") val language: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("score") val score: Int
)

internal fun AudioBookDto.toDomain() = AudioBook(
    audiobookId,
    name,
    authorName,
    duration,
    avatarUrl,
    language,
    releaseDate,
    score
)