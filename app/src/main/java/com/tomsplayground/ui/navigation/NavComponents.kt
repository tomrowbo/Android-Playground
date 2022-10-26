package com.tomsplayground.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tomsplayground.R
import com.tomsplayground.TomsPlaygroundDestinations.HOME_SCREEN
import com.tomsplayground.TomsPlaygroundDestinations.POST_SCREEN
import com.tomsplayground.TomsPlaygroundDestinations.PROFILE_SCREEN
import com.tomsplayground.TomsPlaygroundDestinations.SEARCH_SCREEN

import com.tomsplayground.ui.navigation.NavContent.navItems

object NavContent{
    val navItems = listOf(
        NavComponentItem("Home", HOME_SCREEN, Icons.Default.Home, R.string.home_icon_content_desc),
        NavComponentItem("Search", SEARCH_SCREEN, Icons.Default.Search, R.string.search_icon_content_desc),
        NavComponentItem("Post", POST_SCREEN, Icons.Default.Add, R.string.post_icon_content_desc),
        NavComponentItem("Profile", PROFILE_SCREEN, Icons.Default.Person, R.string.person_icon_content_desc)
    )
}


@Composable
fun PlaygroundNavigationRail(navController: NavController, selectedDestination: String){
    val currentDestination = navController.currentDestination?.navigatorName
    NavigationRail() {
        navItems.forEach{
            NavigationRailItem(
                selected = currentDestination == it.location,
                onClick = {navController.navigate(it.location)},
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = stringResource(id = it.contentDescription)
                    )
                }
            )
        }
    }
}

@Composable
fun PlaygroundNavigationBar(selectedDestination: String) {

}

@Composable
fun PlaygroundNavigationDrawer(){

}

data class NavComponentItem(
    val name: String,
    val location: String,
    val icon: ImageVector,
    val contentDescription: Int
)