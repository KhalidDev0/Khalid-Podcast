package com.khalid.core.feed.usecase

import com.khalid.core.bases.BaseUseCase
import com.khalid.core.feed.repository.FeedRepository
import jakarta.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) : BaseUseCase() {

    operator fun invoke(page: Int = 1) = initUseCase {
        feedRepository.getHomeSections(page)
    }
}