package com.tomsplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tomsplayground.ui.home.HomeScreen
import com.tomsplayground.ui.home.HomeViewModel

@Composable
fun TomsPlaygroundNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TomsPlaygroundDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(TomsPlaygroundDestinations.HOME_ROUTE) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel)
        }
    }
}
