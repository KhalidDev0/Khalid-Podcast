@file:OptIn(ExperimentalCoroutinesApi::class)

package com.khalid.search.presentation


import com.khalid.core.search.model.SearchResponse
import com.khalid.core.search.usecase.GetSearchResultUseCase
import com.khalid.core.utils.Response
import com.khalid.search.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val mainRule = MainDispatcherRule()
    private val useCase: GetSearchResultUseCase = mockk(relaxed = true)

    private val fakeResult = SearchResponse(sections = emptyList())
    private fun stubSearchFlow() = flow {
        emit(Response.Loading())
        delay(50)
        emit(Response.Success(fakeResult))
    }

    @Test
    fun `debounce - only one search call for rapid typing`() = runTest {
        coEvery { useCase.invoke(any()) } returns stubSearchFlow()
        val vm = SearchViewModel(useCase)

        vm.onEvent(SearchEvent.OnTextChanged("Th"))
        advanceTimeBy(50)
        vm.onEvent(SearchEvent.OnTextChanged("Thm"))
        advanceTimeBy(50)
        vm.onEvent(SearchEvent.OnTextChanged("Khalid"))
        advanceTimeBy(100)
        advanceTimeBy(200)
        advanceUntilIdle()

        coVerify(exactly = 1) { useCase.invoke("Khalid") }
    }

    @Test
    fun `loading flag is true only while use case is loading`() = runTest {
        coEvery { useCase.invoke(any()) } returns stubSearchFlow()
        val vm = SearchViewModel(useCase)

        vm.onEvent(SearchEvent.OnTextChanged("audio"))
        advanceTimeBy(250)
        advanceUntilIdle()

        val finalState = vm.state.value
        assertEquals(false, finalState.isSearching)
        assertEquals(fakeResult, finalState.searchResult)
    }
}
