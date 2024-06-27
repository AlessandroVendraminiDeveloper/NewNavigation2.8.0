package com.rawfish.test.ui.dashboard.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rawfish.test.ui.dashboard.DashboardNavigation
import com.rawfish.test.util.fromRoute

@Composable
fun CustomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val previousRoute = navController.previousBackStackEntry?.destination?.route
    val tabRoute = navBackStackEntry.fromRoute(previousRoute = previousRoute)

    AnimatedVisibility(
        visible = tabRoute != null,
        enter = fadeIn(animationSpec = tween(durationMillis = 700)),
        exit = fadeOut(animationSpec = tween(durationMillis = 700)) + slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight }
        )
    ) {
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    selected = tabRoute == item.route,
                    onClick = {
                        navController.navigate(route = item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    label = {
                        Text(text = item.title)
                    },
                    alwaysShowLabel = true,
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount != null) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if (item.hasNews) {
                                    Badge()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        }
                    }
                )
            }
        }
    }
}

data class BottomNavigationItem(
    val route: DashboardNavigation,
    val title: String,
    val icon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val items = listOf(
    BottomNavigationItem(
        route = DashboardNavigation.Home,
        title = "Home",
        icon = Icons.Filled.Home,
        hasNews = false,
    ),
    BottomNavigationItem(
        route = DashboardNavigation.Chat,
        title = "Chat",
        icon = Icons.Filled.Email,
        hasNews = false,
        badgeCount = 45
    ),
    BottomNavigationItem(
        route = DashboardNavigation.Settings,
        title = "Settings",
        icon = Icons.Filled.Settings,
        hasNews = true,
    ),
)