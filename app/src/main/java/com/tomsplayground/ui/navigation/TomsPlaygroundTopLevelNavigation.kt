package com.tomsplayground

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.tomsplayground.ui.navigation.NavComponentItem

/**
 * List of all destinations used in the [TomsPlaygroundApp].
 */
object TomsPlaygroundTopLevelDestinations {
    const val PROFILE_SCREEN = "profile"
    const val HOME_SCREEN = "home"
    const val SEARCH_SCREEN = "search"
    const val POST_SCREEN = "post"
}

val TOP_LEVEL_NAV_DESTINATION_ITEMS = listOf(
    NavComponentItem(
        "Home",
        TomsPlaygroundTopLevelDestinations.HOME_SCREEN,
        Icons.Default.Home,
        R.string.home_icon_content_desc
    ),
    NavComponentItem(
        "Search",
        TomsPlaygroundTopLevelDestinations.SEARCH_SCREEN,
        Icons.Default.Search,
        R.string.search_icon_content_desc
    ),
    NavComponentItem(
        "Post",
        TomsPlaygroundTopLevelDestinations.POST_SCREEN,
        Icons.Default.Add,
        R.string.post_icon_content_desc
    ),
    NavComponentItem(
        "Profile",
        TomsPlaygroundTopLevelDestinations.PROFILE_SCREEN,
        Icons.Default.Person,
        R.string.person_icon_content_desc
    )
)

/**
 * Models the navigation actions in the app.
 */
class TomsPlaygroundNavigationActions(private val navController: NavHostController) {
    fun navigateTo(navItem: NavComponentItem) {
        navController.navigate(navItem.location) {
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
    }
}
