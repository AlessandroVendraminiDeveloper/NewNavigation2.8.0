package com.rawfish.test.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rawfish.test.dispatcher.DispatcherNavigation
import kotlinx.serialization.Serializable

fun NavGraphBuilder.dashboardGraph() {
    composable<DispatcherNavigation.Dashboard> {
        val safeArgs = it.toRoute<DispatcherNavigation.Dashboard>()

        DashboardScreen(args = safeArgs)
    }
}

// Bottom bar screens
@Serializable
sealed class DashboardNavigation {
    @Serializable
    data object Home: DashboardNavigation()

    @Serializable
    data object Chat: DashboardNavigation()

    @Serializable
    data object Settings: DashboardNavigation()
}

// Bottom bar classes
@Serializable
data class ItemDetail(val detailId: String): DashboardNavigation()