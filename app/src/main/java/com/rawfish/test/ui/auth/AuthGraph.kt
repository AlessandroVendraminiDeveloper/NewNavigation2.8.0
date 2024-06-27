package com.rawfish.test.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rawfish.test.dispatcher.DispatcherNavigation
import com.rawfish.test.ui.auth.login.LoginScreen
import com.rawfish.test.ui.auth.signup.SignUpScreen
import kotlinx.serialization.Serializable


fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<DispatcherNavigation.AuthNavigation>(
        startDestination = Login,
    ) {
        composable<Login> {
            LoginScreen(navController = navController)
        }
        composable<SignUp> {
            SignUpScreen(navController = navController)
        }
    }
}

@Serializable
object Login

@Serializable
object SignUp