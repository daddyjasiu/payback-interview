package com.payback.interviewapp.dashboard.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.payback.interviewapp.BuildConfig
import com.payback.interviewapp.base.navigation.Destination
import com.payback.interviewapp.dashboard.data.model.DashboardRequest
import com.payback.interviewapp.dashboard.data.repository.DashboardRepository
import com.payback.interviewapp.dashboard.data.service.DEFAULT_DASHBOARD_QUERY
import com.payback.interviewapp.dashboard.ui.mapper.DashboardUiMapper
import com.payback.interviewapp.dashboard.ui.mapper.UiDashboardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
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
        fetchImages(DEFAULT_DASHBOARD_QUERY)
    }

    fun onUiEvent(event: DashboardUiEvent) {
        when (event) {
            is DashboardUiEvent.GoToDetailsScreen -> goToDetailsScreen(event.dashboardItem)
            is DashboardUiEvent.FetchImages -> fetchImages(event.tags)
        }
    }

    private fun goToDetailsScreen(dashboardItem: UiDashboardItem) {
        val json = Gson().toJson(dashboardItem)
        val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
        navController.navigate("${Destination.Details.route}/$encodedJson")
    }

    private fun fetchImages(tags: String) {
        viewModelScope.launch {
            flow {
                emit(DashboardUiState.Loading)

                val response = repository.getImages(
                    DashboardRequest(
                        key = BuildConfig.API_KEY,
                        query = tags
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

}