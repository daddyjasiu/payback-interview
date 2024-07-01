package com.payback.interviewapp.details.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    private val navController: NavHostController
) : ViewModel() {

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