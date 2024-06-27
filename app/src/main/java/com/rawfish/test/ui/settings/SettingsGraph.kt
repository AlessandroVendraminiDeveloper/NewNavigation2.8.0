package com.rawfish.test.ui.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.rawfish.test.ui.dashboard.DashboardNavigation

fun NavGraphBuilder.settingsGraph(navController: NavHostController) {
    composable<DashboardNavigation.Settings> {
        SettingsScreen(navController = navController)
    }
}