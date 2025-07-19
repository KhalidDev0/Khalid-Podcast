package com.khalid.core_data.services.search

import com.khalid.core.search.model.SearchResponse
import com.khalid.core.utils.Response
import com.khalid.core_data.services.search.dto.SearchResponseDto
import com.khalid.core_data.services.search.dto.toDomain
import com.khalid.core_data.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class SearchRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val api: SearchApi = mockk()
    private lateinit var repo: SearchRepositoryImpl

    @Before
    fun setUp() {
        repo = SearchRepositoryImpl(api)
    }

    @Test
    fun `getSearchResult emits Success with mapped SearchResponse`() = runTest {
        val query = "android"
        val dto = SearchResponseDto(sections = emptyList())
        val mapped: SearchResponse = dto.toDomain()

        coEvery { api.search(query) } returns dto

        val result = repo.getSearchResult(query).first()

        assertEquals(Response.Success(mapped), result)
    }

    @Test
    fun `getSearchResult emits Error when api throws`() = runTest {
        val query = "crash"
        coEvery { api.search(query) } throws IOException("boom")

        val result = repo.getSearchResult(query).first()

        assertTrue(result is Response.Error)
        val error = result as Response.Error
        assertEquals(4000, error.errorCode)
        assertEquals("Unknown error", error.errorMessage)
    }
}
