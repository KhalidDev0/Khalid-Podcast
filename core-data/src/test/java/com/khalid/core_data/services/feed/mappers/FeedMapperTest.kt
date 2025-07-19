package com.khalid.core_data.services.feed.mappers

import com.google.gson.Gson
import com.khalid.core.feed.model.AudioArticleSection
import com.khalid.core.feed.model.AudioBookSection
import com.khalid.core.feed.model.EpisodeSection
import com.khalid.core.feed.model.PodcastSection
import com.khalid.core_data.services.feed.dto.AudioArticleDto
import com.khalid.core_data.services.feed.dto.AudioBookDto
import com.khalid.core_data.services.feed.dto.ContentTypeDto
import com.khalid.core_data.services.feed.dto.EpisodeDto
import com.khalid.core_data.services.feed.dto.FeedResponseDto
import com.khalid.core_data.services.feed.dto.PaginationDto
import com.khalid.core_data.services.feed.dto.PodcastDto
import com.khalid.core_data.services.feed.dto.SectionDto
import com.khalid.core_data.services.feed.dto.SectionLayoutDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FeedMapperTest {

    private val gson = Gson()
    private val mapper = FeedMapper(gson)

    private fun podcastDto(id: String = "pod-1") = PodcastDto(
        podcastId = id,
        name = "Podcast $id",
        description = "desc",
        avatarUrl = "avatar",
        episodeCount = 10,
        duration = 3_600,
        language = "en",
        priority = 1,
        popularityScore = 1,
        score = 1f
    )

    private fun episodeDto(id: String = "ep-1") = EpisodeDto(
        episodeId = id,
        name = "Episode $id",
        podcastName = "Show",
        duration = 600,
        avatarUrl = "avatar",
        audioUrl = "audio",
        releaseDate = "2024-01-01T00:00:00Z",
        score = 123f,
        podcastPriority = 1,
        separatedAudioUrl = "",
        podcastId = "",
    )

    private fun audioBookDto(id: String = "book-1") = AudioBookDto(
        audiobookId = id,
        name = "Book $id",
        authorName = "Author",
        duration = 50_000,
        avatarUrl = "avatar",
        language = "en",
        releaseDate = "2023-01-01",
        score = 90,
        description = ""

    )

    private fun audioArticleDto(id: String = "art-1") = AudioArticleDto(
        articleId = id,
        name = "Article $id",
        authorName = "Author",
        duration = 1_200,
        avatarUrl = "avatar",
        releaseDate = "2022-01-01",
        score = 80,
        description = ""
    )

    private fun payload(section: SectionDto) = FeedResponseDto(
        sections = listOf(section),
        pagination = PaginationDto(nextPage = "next", totalPages = 2)
    )

    private fun section(
        name: String,
        type: SectionLayoutDto = SectionLayoutDto.SQUARE,
        order: Int = 1,
        contentType: ContentTypeDto,
        contentObj: Any
    ) = SectionDto(
        name = name,
        type = type,
        contentType = contentType,
        order = order,
        content = listOf(gson.toJsonTree(contentObj).asJsonObject)
    )

    @Test
    fun `podcast section maps to PodcastSection`() {
        val dto = payload(
            section(
                "pods",
                contentType = ContentTypeDto.PODCAST,
                contentObj = podcastDto()
            )
        )
        val feed = mapper.map(dto)
        assertTrue(feed.sections.single() is PodcastSection)
    }

    @Test
    fun `episode section maps to EpisodeSection`() {
        val dto =
            payload(section("eps", contentType = ContentTypeDto.EPISODE, contentObj = episodeDto()))
        val feed = mapper.map(dto)
        val sec = feed.sections.single()
        assertTrue(sec is EpisodeSection)
        sec as EpisodeSection
        assertEquals("eps", sec.name)
        assertEquals("ep-1", sec.section.content.first().episodeId)
    }

    @Test
    fun `audio book section maps to AudioBookSection`() {
        val dto = payload(
            section(
                "books",
                contentType = ContentTypeDto.AUDIO_BOOK,
                contentObj = audioBookDto()
            )
        )
        val feed = mapper.map(dto)
        val sec = feed.sections.single()
        assertTrue(sec is AudioBookSection)
        sec as AudioBookSection
        assertEquals("Book book-1", sec.section.content.first().name)
    }

    @Test
    fun `audio article section maps to AudioArticleSection`() {
        val dto = payload(
            section(
                "articles",
                contentType = ContentTypeDto.AUDIO_ARTICLE,
                contentObj = audioArticleDto()
            )
        )
        val feed = mapper.map(dto)
        val sec = feed.sections.single()
        assertTrue(sec is AudioArticleSection)
        sec as AudioArticleSection
        assertEquals("art-1", sec.section.content.first().articleId)
    }
}
