package com.khalid.core.feed.model

sealed interface FeedSection {
    val section: Section<out Media>
    val name get() = section.name
    val layout get() = section.layout
    val order get() = section.order
    val contentType get() = section.contentType
}

data class PodcastSection(
    override val section: Section<Podcast>
) : FeedSection

data class EpisodeSection(
    override val section: Section<Episode>
) : FeedSection

data class AudioBookSection(
    override val section: Section<AudioBook>
) : FeedSection

data class AudioArticleSection(
    override val section: Section<AudioArticle>
) : FeedSection
