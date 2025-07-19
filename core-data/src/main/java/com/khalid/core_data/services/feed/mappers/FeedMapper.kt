package com.khalid.core_data.services.feed.mappers

import com.google.gson.Gson
import com.khalid.core.feed.model.AudioArticleSection
import com.khalid.core.feed.model.AudioBookSection
import com.khalid.core.feed.model.EpisodeSection
import com.khalid.core.feed.model.Feed
import com.khalid.core.feed.model.FeedSection
import com.khalid.core.feed.model.Pagination
import com.khalid.core.feed.model.PodcastSection
import com.khalid.core.feed.model.Section
import com.khalid.core_data.services.feed.dto.AudioArticleDto
import com.khalid.core_data.services.feed.dto.AudioBookDto
import com.khalid.core_data.services.feed.dto.ContentTypeDto
import com.khalid.core_data.services.feed.dto.EpisodeDto
import com.khalid.core_data.services.feed.dto.FeedResponseDto
import com.khalid.core_data.services.feed.dto.PodcastDto
import com.khalid.core_data.services.feed.dto.SectionDto
import com.khalid.core_data.services.feed.dto.toCore
import com.khalid.core_data.services.feed.dto.toDomain
import javax.inject.Inject

internal class FeedMapper @Inject constructor(
    private val gson: Gson
) {

    fun map(dto: FeedResponseDto): Feed = Feed(
        sections = dto.sections.mapNotNull(::mapSectionSafely),
        pagination = dto.pagination.run { Pagination(nextPage, totalPages) }
    )

    private fun mapSectionSafely(dto: SectionDto): FeedSection? = runCatching {
        when (dto.contentType) {
            ContentTypeDto.PODCAST -> PodcastSection(
                Section(
                    name = dto.name,
                    layout = dto.type.toCore(),
                    contentType = dto.contentType.toCore(),
                    order = dto.order,
                    content = dto.content.map {
                        gson.fromJson(it, PodcastDto::class.java).toDomain()
                    }
                )
            )

            ContentTypeDto.EPISODE -> EpisodeSection(
                Section(
                    name = dto.name,
                    layout = dto.type.toCore(),
                    contentType = dto.contentType.toCore(),
                    order = dto.order,
                    content = dto.content.map {
                        gson.fromJson(it, EpisodeDto::class.java).toDomain()
                    }
                )
            )

            ContentTypeDto.AUDIO_BOOK -> AudioBookSection(
                Section(
                    name = dto.name,
                    layout = dto.type.toCore(),
                    contentType = dto.contentType.toCore(),
                    order = dto.order,
                    content = dto.content.map {
                        gson.fromJson(it, AudioBookDto::class.java).toDomain()
                    }
                )
            )

            ContentTypeDto.AUDIO_ARTICLE -> AudioArticleSection(
                Section(
                    name = dto.name,
                    layout = dto.type.toCore(),
                    contentType = dto.contentType.toCore(),
                    order = dto.order,
                    content = dto.content.map {
                        gson.fromJson(it, AudioArticleDto::class.java).toDomain()
                    }
                )
            )

            ContentTypeDto.UNKNOWN -> null
        }
    }.getOrNull()
}
