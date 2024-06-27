package com.rawfish.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.rawfish.test.ui.home.HomeScreen
import com.rawfish.test.ui.theme.TestTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                val navController = rememberNavController()
                var currentDestination by remember {
                    mutableStateOf<RootNavigation>(RootNavigation.CustomSplashScreen)
                }

                NavHost(
                    navController = navController,
                    startDestination = currentDestination,
                ) {
                    composable<RootNavigation.CustomSplashScreen> {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {
                                Button(
                                    onClick = {
                                        currentDestination = RootNavigation.AuthNavigation
                                    }
                                ) {
                                    Text(text = "Go to login flow")
                                }
                                Button(
                                    onClick = {
                                        currentDestination = RootNavigation.HomeScreen(userId = "1234")
                                    }
                                ) {
                                    Text(text = "Go to home flow")
                                }
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
                    composable<RootNavigation.HomeScreen> {
                        val safeArgs = it.toRoute<RootNavigation.HomeScreen>()

                        HomeScreen(args = safeArgs)
                    }
                }
            }
        }
    }
}

@Serializable
sealed class RootNavigation {
    @Serializable
    data object CustomSplashScreen: RootNavigation()
    
    @Serializable
    data object AuthNavigation: RootNavigation()
    
    @Serializable
    data class HomeScreen(val userId: String): RootNavigation()
}

@Serializable
object LoginScreen

@Serializable
object SignUpScreen

