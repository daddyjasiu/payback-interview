package com.payback.interviewapp.details.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    private val navController: NavHostController
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> get() = _uiState

    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = DetailsUiState.Loaded
        }
    }

    fun onUiEvent(event: DetailsUiEvent) {
        when (event) {
            is DetailsUiEvent.GoBack -> goBack()
        }
    }

    private fun goBack() {
        if (navController.previousBackStackEntry != null) {
            navController.navigateUp()
        }
    }

}