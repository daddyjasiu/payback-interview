package com.payback.interviewapp.base.navigation

sealed class Destination(val route: String) {
    data object Dashboard : Destination(ERoute.dashboard.name)
    data object Details : Destination(ERoute.details.name)
}

enum class ERoute {
    dashboard,
    details,
}
