package com.rawfish.test.ui.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rawfish.test.ui.dashboard.ItemDetail

fun NavGraphBuilder.detailGraph(navController: NavHostController) {
    composable<ItemDetail> {
        val safeArgs = it.toRoute<ItemDetail>()

        DetailScreen(
            navController = navController,
            safeArgs = safeArgs
        )
    }
}