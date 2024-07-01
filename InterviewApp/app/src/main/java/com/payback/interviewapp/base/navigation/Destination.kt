package com.payback.interviewapp.base.navigation

sealed class Destination(val route: String) {
    data object Dashboard : Destination(ERoute.DASHBOARD.name)
    data object Details : Destination(ERoute.DETAILS.name)
}

enum class ERoute {
    DASHBOARD,
    DETAILS,
}
