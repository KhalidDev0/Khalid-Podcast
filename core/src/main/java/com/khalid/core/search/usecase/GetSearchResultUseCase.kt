package com.khalid.core.search.usecase

import com.khalid.core.bases.BaseUseCase
import com.khalid.core.search.reposirtory.SearchRepository
import jakarta.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseUseCase() {

    operator fun invoke(searchValue: String) = initUseCase {
        searchRepository.getSearchResult(searchValue)
    }
}