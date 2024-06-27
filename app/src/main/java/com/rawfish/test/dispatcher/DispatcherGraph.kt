package com.rawfish.test.dispatcher

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.dispatcherGraph(navController: NavHostController) {
    composable<DispatcherNavigation.Dispatcher>{
        DispatcherScreen(navController = navController)
    }
}

@Serializable
sealed class DispatcherNavigation {
    @Serializable
    data object Dispatcher: DispatcherNavigation()

    @Serializable
    data object AuthNavigation: DispatcherNavigation()

    @Serializable
    data class Dashboard(val userId: String): DispatcherNavigation()
}