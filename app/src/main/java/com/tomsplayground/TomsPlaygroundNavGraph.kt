package com.tomsplayground

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tomsplayground.ui.home.HomeRoute
import com.tomsplayground.ui.theme.TomsPlaygroundTheme

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
        composable(TomsPlaygroundDestinations.HOME_ROUTE){
            HomeRoute()
        }
    }
}

