package com.rawfish.test.ui.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rawfish.test.dispatcher.DispatcherNavigation
import com.rawfish.test.ui.auth.authGraph
import com.rawfish.test.ui.chat.chatGraph
import com.rawfish.test.ui.dashboard.component.CustomNavigationBar
import com.rawfish.test.ui.detail.detailGraph
import com.rawfish.test.ui.home.homeGraph
import com.rawfish.test.ui.settings.settingsGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(args: DispatcherNavigation.Dashboard) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomNavigationBar(navController = navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        // We don't need to use innerPadding if views has scaffold
        NavHost(
            navController = navController,
            startDestination = DashboardNavigation.Home
        ) {
            homeGraph(
                navController = navController,
                dashboardSafeArgs = args,
            )
            chatGraph(navController = navController,)
            settingsGraph(navController = navController)
            detailGraph(navController = navController,)
            authGraph(navController = navController)
        }
    }
}