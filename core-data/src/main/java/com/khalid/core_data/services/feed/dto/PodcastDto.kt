package com.khalid.core_data.services.feed.dto

import com.google.gson.annotations.SerializedName
import com.khalid.core.feed.model.Podcast

internal data class PodcastDto(
    @SerializedName("podcast_id") val podcastId: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("episode_count") val episodeCount: Int,
    @SerializedName("duration") val duration: Long,
    @SerializedName("language") val language: String?,
    @SerializedName("priority") val priority: Int?,
    @SerializedName("popularityScore") val popularityScore: Int?,
    @SerializedName("score") val score: Float?
)

internal fun PodcastDto.toDomain() = Podcast(
    podcastId,
    name,
    description,
    avatarUrl,
    episodeCount,
    duration,
    language,
    priority,
    popularityScore,
    score
)
