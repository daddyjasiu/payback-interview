package com.payback.interviewapp.base.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.payback.interviewapp.dashboard.ui.view.DashboardScreen
import com.payback.interviewapp.dashboard.ui.viewmodel.DashboardViewModel
import com.payback.interviewapp.dashboard.ui.mapper.UiDashboardItem
import com.payback.interviewapp.details.ui.view.DetailsScreen
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
        composable(
            route = "${Destination.Details.route}/{dashboardItem}",
            arguments = listOf(navArgument("dashboardItem") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("dashboardItem")
            val dashboardItem = Gson().fromJson(json, UiDashboardItem::class.java)
            val viewModel = hiltViewModel<DetailsViewModel>()
            DetailsScreen(dashboardItem = dashboardItem, onUiEvent = viewModel::onUiEvent)
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