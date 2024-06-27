package com.rawfish.test.dispatcher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DispatcherScreen(navController: NavController) {
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
                    navController.navigate(DispatcherNavigation.AuthNavigation) {
                        popUpTo<DispatcherNavigation.Dispatcher> {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "Go to auth flow")
            }
            Button(
                onClick = {
                    navController.navigate(
                        DispatcherNavigation.Dashboard(
                            userId = "Test1234"
                        )
                    ) {
                        popUpTo<DispatcherNavigation.Dispatcher> {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "Go to home flow")
            }
        }
    }
}