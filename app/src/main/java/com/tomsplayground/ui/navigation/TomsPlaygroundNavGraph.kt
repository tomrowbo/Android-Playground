package com.tomsplayground.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tomsplayground.TomsPlaygroundTopLevelDestinations
import com.tomsplayground.ui.home.HomeScreen
import com.tomsplayground.ui.home.HomeViewModel
import com.tomsplayground.ui.profile.ProfileScreen
import com.tomsplayground.ui.profile.ProfileViewModel
import com.tomsplayground.ui.utils.PlaygroundContentType

@Composable
fun TomsPlaygroundNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TomsPlaygroundTopLevelDestinations.HOME_SCREEN,
    widthSize: WindowWidthSizeClass,
    contentType: PlaygroundContentType
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(TomsPlaygroundTopLevelDestinations.HOME_SCREEN) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel, contentType)
        }
        composable(TomsPlaygroundTopLevelDestinations.PROFILE_SCREEN) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(widthSize, viewModel)
        }
    }
}
