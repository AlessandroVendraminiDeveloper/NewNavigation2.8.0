package com.rawfish.test.util

import androidx.navigation.NavBackStackEntry
import com.rawfish.test.ui.dashboard.DashboardNavigation
import com.rawfish.test.ui.dashboard.ItemDetail

fun NavBackStackEntry?.fromRoute(previousRoute: String?): DashboardNavigation? {
    val parentRoute = previousRoute?.trimSimplifiedRoute()

    this?.destination?.route?.trimSimplifiedRoute().let {
        when (it) {
            DashboardNavigation.Home::class.simpleName -> return DashboardNavigation.Home
            DashboardNavigation.Chat::class.simpleName -> return DashboardNavigation.Chat
            DashboardNavigation.Settings::class.simpleName -> return DashboardNavigation.Settings
            ItemDetail::class.simpleName -> when (parentRoute) {
                DashboardNavigation.Home::class.simpleName -> return DashboardNavigation.Home
                DashboardNavigation.Chat::class.simpleName -> return DashboardNavigation.Chat
                DashboardNavigation.Settings::class.simpleName -> return DashboardNavigation.Settings
                else -> return DashboardNavigation.Home
            }
            else -> return null
        }
    }
}

private fun String.trimSimplifiedRoute(): String {
    return this.substringBefore("?")
        .substringBefore("/")
        .substringAfterLast(".")
}