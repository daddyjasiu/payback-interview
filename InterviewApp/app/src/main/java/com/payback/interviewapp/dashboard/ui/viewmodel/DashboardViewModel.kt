package com.payback.interviewapp.dashboard.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.payback.interviewapp.BuildConfig
import com.payback.interviewapp.base.navigation.Destination
import com.payback.interviewapp.dashboard.data.model.DashboardRequest
import com.payback.interviewapp.dashboard.data.repository.DashboardRepository
import com.payback.interviewapp.dashboard.data.service.DEFAULT_DASHBOARD_QUERY
import com.payback.interviewapp.dashboard.data.service.DashboardItemDao
import com.payback.interviewapp.dashboard.ui.mapper.DashboardUiMapper
import com.payback.interviewapp.dashboard.ui.model.UiDashboardItem
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
    private val dao: DashboardItemDao,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> get() = _uiState

    init {
        fetchItems(DEFAULT_DASHBOARD_QUERY)
    }

    fun onUiEvent(event: DashboardUiEvent) {
        when (event) {
            is DashboardUiEvent.GoToDetailsScreen -> goToDetailsScreen(event.dashboardItem)
            is DashboardUiEvent.FetchItems -> fetchItems(event.tags)
        }
    }

    private fun goToDetailsScreen(dashboardItem: UiDashboardItem) {
        val json = Gson().toJson(dashboardItem)
        val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
        navController.navigate("${Destination.Details.route}/$encodedJson")
    }

    private fun fetchItems(tags: String) {
        viewModelScope.launch {
            flow {
                emit(DashboardUiState.Loading)
                val response = try {
                    val items = repository.getItems(
                        DashboardRequest(
                            key = BuildConfig.API_KEY,
                            query = tags
                        )
                    )
                    val mappedItems = uiMapper.invoke(items)
                    dao.clearAll()
                    dao.insertAll(uiMapper.mapUiModelToCacheEntity(mappedItems))
                    mappedItems
                } catch (e: Exception) {
                    val items = repository.getCachedItems()
                    uiMapper.mapCacheEntityToUiModel(items).takeIf { items.isNotEmpty() }
                }

                response?.let {
                    emit(DashboardUiState.Loaded(response))
                } ?: emit(DashboardUiState.Error(Exception()))
            }
                .catch {
                    _uiState.value = DashboardUiState.Error(it)
                }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }
}
