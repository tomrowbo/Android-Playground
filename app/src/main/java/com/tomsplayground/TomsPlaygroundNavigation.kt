package com.tomsplayground

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * List of all destinations used in the [TomsPlaygroundApp].
 */
object TomsPlaygroundDestinations {
    const val PROFILE_SCREEN =  "profile"
    const val HOME_SCREEN = "home"
}

/**
 * Models the navigation actions in the app.
 */
class TomsPlaygroundNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(TomsPlaygroundDestinations.HOME_SCREEN) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
        val navigateToProfile: () -> Unit = {
            navController.navigate(TomsPlaygroundDestinations.PROFILE_SCREEN) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}
