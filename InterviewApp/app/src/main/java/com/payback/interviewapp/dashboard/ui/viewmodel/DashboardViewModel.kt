package com.payback.interviewapp.dashboard.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.payback.interviewapp.base.navigation.Destination
import com.payback.interviewapp.dashboard.data.model.DashboardRequest
import com.payback.interviewapp.dashboard.data.repository.DashboardRepository
import com.payback.interviewapp.details.ui.mapper.DashboardUiMapper
import com.payback.interviewapp.details.ui.mapper.UiDashboardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DashboardViewModel @Inject constructor(
    private val navController: NavHostController,
    private val repository: DashboardRepository,
    private val uiMapper: DashboardUiMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> get() = _uiState

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            flow {
                emit(DashboardUiState.Loading)

                val response = repository.getImages(
                    DashboardRequest(
                        key = "44719350-f487ca31df1dd432e2633d430",
                        query = "fruits"
                    )
                )
                val dashboardItems = uiMapper.invoke(response)

                emit(
                    DashboardUiState.Loaded(
                        dashboardItems = dashboardItems
                    )
                )

            }
                .catch { Log.d("ESSA", it.toString()) }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }

    fun onUiEvent(event: DashboardUiEvent) {
        when (event) {
            is DashboardUiEvent.GoToDetailsScreen -> goToDetailsScreen(event.dashboardItem)
        }
    }

    private fun goToDetailsScreen(dashboardItem: UiDashboardItem) {
        navController.navigate(Destination.Details.route)
    }
}