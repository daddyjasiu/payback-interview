package com.payback.interviewapp.dashboard.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.payback.interviewapp.base.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DashboardViewModel @Inject constructor(
    private val navController: NavHostController
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> get() = _uiState

    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = DashboardUiState.Loaded("siabadaba")
        }
    }

    fun onUiEvent(event: DashboardUiEvent) {
        when (event) {
            is DashboardUiEvent.GoToDetailsScreen -> goToDetailsScreen(event.url)
        }
    }

    private fun goToDetailsScreen(url: String) {
        navController.navigate(Destination.Details.route)
    }

}