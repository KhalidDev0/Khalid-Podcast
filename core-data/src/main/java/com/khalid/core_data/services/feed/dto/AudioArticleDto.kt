package com.khalid.core_data.services.feed.dto

import com.google.gson.annotations.SerializedName
import com.khalid.core.feed.model.AudioArticle

internal data class AudioArticleDto(
    @SerializedName("article_id") val articleId: String,
    @SerializedName("name") val name: String,
    @SerializedName("author_name") val authorName: String,
    @SerializedName("description") val description: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("duration") val duration: Long,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("score") val score: Int
)

internal fun AudioArticleDto.toDomain() = AudioArticle(
    articleId,
    name,
    authorName,
    duration,
    avatarUrl,
    releaseDate,
    score
)