package com.rawfish.test.ui.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.rawfish.test.ui.dashboard.DashboardNavigation

fun NavGraphBuilder.chatGraph(navController: NavHostController) {
    composable<DashboardNavigation.Chat> {
        ChatScreen(navController = navController)
    }
}