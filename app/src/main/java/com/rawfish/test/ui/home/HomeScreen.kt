package com.rawfish.test.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rawfish.test.dispatcher.DispatcherNavigation
import com.rawfish.test.ui.dashboard.ItemDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    args: DispatcherNavigation.Dashboard
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            // .padding(navigationBarInnerPadding)
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(text = "User id is: ${args.userId}")
            Button(
                onClick = {
                    navController.navigate(
                        route = ItemDetail(
                            detailId = "home-1234"
                        )
                    )
                }
            ) {
                Text(text = "Go to detail view")
            }
        }
    }
}