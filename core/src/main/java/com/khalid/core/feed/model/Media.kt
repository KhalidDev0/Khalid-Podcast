package com.khalid.core.feed.model

sealed interface Media

data class Podcast(
    val podcastId: String,
    val name: String,
    val description: String,
    val avatarUrl: String,
    val episodeCount: Int,
    val duration: Long,
    val language: String?,
    val priority: Int? = null,
    val popularityScore: Int? = null,
    val score: Float? = null
) : Media

data class Episode(
    val episodeId: String,
    val name: String,
    val podcastName: String,
    val podcastPriority: Int?,
    val duration: Long,
    val avatarUrl: String,
    val audioUrl: String,
    val releaseDate: String,
    val score: Float
) : Media

data class AudioBook(
    val audiobookId: String,
    val name: String,
    val authorName: String,
    val duration: Long,
    val avatarUrl: String,
    val language: String? = null,
    val releaseDate: String,
    val score: Int
) : Media

data class AudioArticle(
    val articleId: String,
    val name: String,
    val authorName: String,
    val duration: Long,
    val avatarUrl: String,
    val releaseDate: String,
    val score: Int
) : Media
