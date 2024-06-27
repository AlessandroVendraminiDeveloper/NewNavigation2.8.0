package com.rawfish.test.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.rawfish.test.dispatcher.DispatcherNavigation
import com.rawfish.test.ui.dashboard.DashboardNavigation

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    dashboardSafeArgs: DispatcherNavigation.Dashboard,
) {
    composable<DashboardNavigation.Home> {
        HomeScreen(
            navController = navController,
            args = dashboardSafeArgs,
        )
    }
}