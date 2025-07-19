package com.khalid.core_data.services.feed.dto

import com.google.gson.annotations.SerializedName
import com.khalid.core.feed.model.Episode

internal data class EpisodeDto(
    @SerializedName("podcastPriority") val podcastPriority: Int?,
    @SerializedName("episode_id") val episodeId: String,
    @SerializedName("name") val name: String,
    @SerializedName("podcast_name") val podcastName: String,
    @SerializedName("duration") val duration: Long,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("separated_audio_url") val separatedAudioUrl: String?,
    @SerializedName("audio_url") val audioUrl: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("podcast_id") val podcastId: String?,
    @SerializedName("score") val score: Float
)

internal fun EpisodeDto.toDomain() = Episode(
    episodeId,
    name,
    podcastName,
    podcastPriority,
    duration,
    avatarUrl,
    audioUrl,
    releaseDate,
    score
)
