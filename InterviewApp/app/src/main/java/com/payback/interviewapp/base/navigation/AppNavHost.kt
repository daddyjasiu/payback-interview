package com.payback.interviewapp.base.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.payback.interviewapp.dashboard.ui.view.DashboardScreen
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardViewModel
import com.payback.interviewapp.details.ui.view.DetailsScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.payback.interviewapp.details.ui.viewmodel.DetailsViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Destination.Dashboard.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destination.Dashboard.route) {
            val viewModel = hiltViewModel<DashboardViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            DashboardScreen(uiState = uiState, onUiEvent = viewModel::onUiEvent)
        }
        composable(Destination.Details.route) {
            val viewModel = hiltViewModel<DetailsViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            DetailsScreen(uiState = uiState, onUiEvent = viewModel::onUiEvent)
        }
    }
}

class NavigationService(
    context: Context,
) {
    val navController = NavHostController(context).apply {
        navigatorProvider.addNavigator(ComposeNavigator())
        navigatorProvider.addNavigator(DialogNavigator())
    }
}