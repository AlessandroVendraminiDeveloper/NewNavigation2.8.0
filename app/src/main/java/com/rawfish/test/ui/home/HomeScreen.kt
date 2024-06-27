package com.rawfish.test.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.rawfish.test.LoginScreen
import com.rawfish.test.RootNavigation
import com.rawfish.test.SignUpScreen
import kotlinx.serialization.Serializable

data class BottomNavigationItem(
    val route: BottomBarNavigation,
    val title: String,
    val icon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val items = listOf(
    BottomNavigationItem(
        route = BottomBarNavigation.HomeScreen,
        title = "Home",
        icon = Icons.Filled.Home,
        hasNews = false,
    ),
    BottomNavigationItem(
        route = BottomBarNavigation.ChatScreen,
        title = "Chat",
        icon = Icons.Filled.Email,
        hasNews = false,
        badgeCount = 45
    ),
    BottomNavigationItem(
        route = BottomBarNavigation.SettingsScreen,
        title = "Settings",
        icon = Icons.Filled.Settings,
        hasNews = true,
    ),
)

@Composable
fun HomeScreen(args: RootNavigation.HomeScreen) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
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
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarNavigation.HomeScreen
        ) {
            composable<BottomBarNavigation.HomeScreen> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(text = "You are in home")
                    Text(text = "User id is: ${args.userId}")
                    Button(
                        onClick = {
                            navController.navigate(
                                route = DetailScreen(
                                    detailId = "6789Zxc"
                                )
                            )
                        }
                    ) {
                        Text(text = "Go to detail view")
                    }
                }
            }
            composable<BottomBarNavigation.ChatScreen> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(text = "You are in chat")
                    Text(text = "User id is: ${args.userId}")
                    Button(
                        onClick = {
                            navController.navigate(route = RootNavigation.AuthNavigation)
                        }
                    ) {
                        Text(text = "Go to new graph nested")
                    }
                }
            }
            composable<BottomBarNavigation.SettingsScreen> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(text = "You are in settings")
                    Text(text = "User id is: ${args.userId}")
                    Button(
                        onClick = {
                            navController.navigate(
                                route = DetailScreen(
                                    detailId = "1234Abc"
                                )
                            )
                        }
                    ) {
                        Text(text = "Go to detail view")
                    }
                }
            }

            ////// Other ///////
            composable<DetailScreen> {
                val safeArgs = it.toRoute<DetailScreen>()

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(text = "You are in detail")
                    Text(text = "You get id: ${safeArgs.detailId}")
                    Button(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Text(text = "Go back")
                    }
                }
            }
            navigation<RootNavigation.AuthNavigation>(
                startDestination = LoginScreen,
            ) {
                composable<LoginScreen> {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            Text(text = "You are in Login Veew")
                            Button(
                                onClick = {
                                    navController.navigate(route = SignUpScreen)
                                }
                            ) {
                                Text(text = "Go to signUp view")
                            }
                        }
                    }
                }
                composable<SignUpScreen> {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            Text(text = "You are in SignUp")
                            Button(
                                onClick = {
                                    navController.navigateUp()
                                }
                            ) {
                                Text(text = "Go back")
                            }
                        }
                    }
                }
            }
        }
    }
}

fun NavBackStackEntry?.fromRoute(previousRoute: String?): BottomBarNavigation? {
    val parentRoute = previousRoute?.trimSimplifiedRoute()

    this?.destination?.route?.trimSimplifiedRoute().let {
        when (it) {
            BottomBarNavigation.HomeScreen::class.simpleName -> return BottomBarNavigation.HomeScreen
            BottomBarNavigation.ChatScreen::class.simpleName -> return BottomBarNavigation.ChatScreen
            BottomBarNavigation.SettingsScreen::class.simpleName -> return BottomBarNavigation.SettingsScreen
            DetailScreen::class.simpleName -> when (parentRoute) {
                BottomBarNavigation.HomeScreen::class.simpleName -> return BottomBarNavigation.HomeScreen
                BottomBarNavigation.ChatScreen::class.simpleName -> return BottomBarNavigation.ChatScreen
                BottomBarNavigation.SettingsScreen::class.simpleName -> return BottomBarNavigation.SettingsScreen
                else -> return BottomBarNavigation.HomeScreen
            }
            else -> return null
        }
    }
}

fun String.trimSimplifiedRoute(): String {
    return this.substringBefore("?")
        .substringBefore("/")
        .substringAfterLast(".")
}

@Serializable
sealed class BottomBarNavigation {
    @Serializable
    data object HomeScreen: BottomBarNavigation()

    @Serializable
    data object ChatScreen: BottomBarNavigation()

    @Serializable
    data object SettingsScreen: BottomBarNavigation()
}

@Serializable
data class DetailScreen(val detailId: String): BottomBarNavigation()