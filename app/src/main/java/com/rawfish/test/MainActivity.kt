package com.rawfish.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rawfish.test.dispatcher.DispatcherNavigation
import com.rawfish.test.dispatcher.dispatcherGraph
import com.rawfish.test.ui.auth.authGraph
import com.rawfish.test.ui.dashboard.dashboardGraph
import com.rawfish.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = DispatcherNavigation.Dispatcher,
                ) {
                    dispatcherGraph(navController = navController)
                    authGraph(navController = navController)
                    dashboardGraph()
                }
            }
        }
    }
}

