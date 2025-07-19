package com.khalid.feed.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.khalid.core.feed.model.EpisodeSection
import com.khalid.core.feed.model.SectionLayout
import com.khalid.core_ui.common.SectionWithTitle
import com.khalid.core_ui.common.listing.bigSquare.BigSquareCardUiModel
import com.khalid.core_ui.common.listing.bigSquare.BigSquareRowView
import com.khalid.core_ui.common.listing.queue.QueueCardUiModel
import com.khalid.core_ui.common.listing.queue.QueueRowView
import com.khalid.core_ui.common.listing.square.SquareCardUiModel
import com.khalid.core_ui.common.listing.square.SquareRowView
import com.khalid.core_ui.common.listing.twoLineGrid.TwoLineGridCardUiModel
import com.khalid.core_ui.common.listing.twoLineGrid.TwoLineGridRowView
import kotlin.random.Random

@Composable
fun EpisodeFactory(
    section: EpisodeSection
) {
    SectionWithTitle(
        title = section.name,
    ) {
        when (section.layout) {
            SectionLayout.SQUARE -> {
                val squareCardData by remember(section.section.content) {
                    derivedStateOf {
                        section.section.content.sortedByDescending { it.podcastPriority }.map {
                            SquareCardUiModel(
                                title = it.name,
                                imageUrl = it.avatarUrl,
                                duration = it.duration,
                                date = it.releaseDate,
                            )
                        }
                    }
                }

                SquareRowView(squareCardData = squareCardData)
            }

            SectionLayout.BIG_SQUARE -> {
                val bigSquareCardData by remember(section.section.content) {
                    derivedStateOf {
                        section.section.content.sortedByDescending { it.podcastPriority }.map {
                            val randomEpisodes = Random.nextInt(1, 50)
                            BigSquareCardUiModel(
                                title = it.name,
                                imageUrl = it.avatarUrl,
                                totalEpisodes = randomEpisodes,
                            )
                        }
                    }
                }

                BigSquareRowView(bigSquareCardData = bigSquareCardData)
            }

            SectionLayout.QUEUE -> {
                val queueCardData by remember(section.section.content) {
                    derivedStateOf {
                        section.section.content.sortedByDescending { it.podcastPriority }.map {
                            val randomEpisodes = Random.nextInt(1, 50)
                            QueueCardUiModel(
                                title = it.name,
                                imageUrl = it.avatarUrl,
                                totalEpisodes = randomEpisodes,
                            )
                        }
                    }
                }

                QueueRowView(queueCardData = queueCardData)
            }

            SectionLayout.TWO_LINES_GRID -> {
                val twoLineGridCardData by remember(section.section.content) {
                    derivedStateOf {
                        section.section.content.sortedByDescending { it.podcastPriority }.map {
                            val randomProgress = Random.nextFloat()
                            TwoLineGridCardUiModel(
                                title = it.name,
                                imageUrl = it.avatarUrl,
                                duration = it.duration,
                                date = it.releaseDate,
                                progress = randomProgress
                            )
                        }
                    }
                }

                TwoLineGridRowView(twoLineGridCardData = twoLineGridCardData)
            }
        }
    }
}