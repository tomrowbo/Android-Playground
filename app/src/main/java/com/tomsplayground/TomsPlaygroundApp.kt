package com.tomsplayground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tomsplayground.ui.theme.TomsPlaygroundTheme
import kotlinx.coroutines.launch

@Composable
fun TomsPlaygroundApp() {
    TomsPlaygroundTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            TomsPlaygroundNavigationActions(navController)
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: TomsPlaygroundDestinations.HOME_ROUTE
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TomsPlaygroundNavGraph(
                navController = navController,
            )
        }
    }
}