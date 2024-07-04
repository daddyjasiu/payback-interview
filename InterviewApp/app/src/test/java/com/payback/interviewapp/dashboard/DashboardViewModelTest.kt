package com.payback.interviewapp.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.payback.interviewapp.base.navigation.Destination
import com.payback.interviewapp.dashboard.data.model.DashboardResponse
import com.payback.interviewapp.dashboard.data.model.mockDashboardCacheEntity
import com.payback.interviewapp.dashboard.data.model.mockPixabayHit
import com.payback.interviewapp.dashboard.data.repository.DashboardRepository
import com.payback.interviewapp.dashboard.data.service.DashboardItemDao
import com.payback.interviewapp.dashboard.ui.mapper.DashboardUiMapper
import com.payback.interviewapp.dashboard.ui.model.mockUiDashboardItem
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardUiEvent
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardUiState
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val navController: NavHostController = mockk(relaxed = true)
    private val repository: DashboardRepository = mockk()
    private val uiMapper: DashboardUiMapper = mockk()
    private val dao: DashboardItemDao = mockk()

    private lateinit var underTest: DashboardViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `fetchItems with success`() = runTest {
        // Arrange
        val response = generateResponse()
        coEvery { repository.getItems(any()) } returns response
        coEvery { repository.getCachedItems() } returns listOf(mockDashboardCacheEntity)
        coEvery { uiMapper.mapCacheEntityToUiModel(any()) } returns listOf(mockUiDashboardItem)

        // Act
        underTest = initViewModel()

        // Verify
        assert(underTest.uiState.value is DashboardUiState.Loading)
        advanceUntilIdle()
        assert(underTest.uiState.value is DashboardUiState.Loaded)
    }

    @Test
    fun `fetchItems with error`() = runTest {
        // Arrange
        val exception = Exception("Network error")
        coEvery { repository.getItems(any()) } throws exception
        coEvery { repository.getCachedItems() } returns emptyList()
        coEvery { uiMapper.mapCacheEntityToUiModel(any()) } returns emptyList()

        // Act
        underTest = initViewModel()

        advanceUntilIdle()

        // Verify
        assert(underTest.uiState.value is DashboardUiState.Error)
    }

    @Test
    fun `goToDetailsScreen navigates correctly`() {
        // Arrange
        val dashboardItem = mockUiDashboardItem
        val json = Gson().toJson(dashboardItem)
        val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())

        // Act
        underTest = initViewModel()
        underTest.onUiEvent(DashboardUiEvent.GoToDetailsScreen(dashboardItem))

        // Verify
        verify { navController.navigate("${Destination.Details.route}/$encodedJson") }
    }

    private fun generateResponse() = DashboardResponse(
        total = 100,
        totalHits = 100,
        hits = listOf(
            mockPixabayHit,
            mockPixabayHit,
            mockPixabayHit,
            mockPixabayHit,
            mockPixabayHit,
        )
    )

    private fun initViewModel() = DashboardViewModel(
        repository = repository,
        uiMapper = uiMapper,
        dao = dao,
        navController = navController,
    )
}
