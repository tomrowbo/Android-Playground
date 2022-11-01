package com.tomsplayground

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.tomsplayground.NavContent.navItems
import com.tomsplayground.TomsPlaygroundDestinations.HOME_SCREEN
import com.tomsplayground.ui.theme.TomsPlaygroundTheme
import com.tomsplayground.ui.utils.*

@Composable
fun TomsPlaygroundApp(windowSize: WindowSizeClass, displayFeatures: List<DisplayFeature>) {
    TomsPlaygroundTheme {
        /**
         * This will help us select type of navigation and content type depending on window size and
         * fold state of the device.
         */
        val navigationType: PlaygroundNavigationType
        val contentType: PlaygroundContentType

        /**
         * We are using display's folding features to map the device postures a fold is in.
         * In the state of folding device If it's half fold in BookPosture we want to avoid content
         * at the crease/hinge
         */
        val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()

        val foldingDevicePosture = when {
            isBookPosture(foldingFeature) ->
                DevicePosture.BookPosture(foldingFeature.bounds)

            isSeparating(foldingFeature) ->
                DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

            else -> DevicePosture.NormalPosture
        }

        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                navigationType = PlaygroundNavigationType.BOTTOM_NAVIGATION
                contentType = PlaygroundContentType.SINGLE_PANE
            }
            WindowWidthSizeClass.Medium -> {
                navigationType = PlaygroundNavigationType.NAVIGATION_RAIL
                contentType = if (foldingDevicePosture != DevicePosture.NormalPosture) {
                    PlaygroundContentType.DUAL_PANE
                } else {
                    PlaygroundContentType.SINGLE_PANE
                }
            }
            WindowWidthSizeClass.Expanded -> {
                navigationType = if (foldingDevicePosture is DevicePosture.BookPosture) {
                    PlaygroundNavigationType.NAVIGATION_RAIL
                } else {
                    PlaygroundNavigationType.PERMANENT_NAVIGATION_DRAWER
                }
                contentType = PlaygroundContentType.DUAL_PANE
            }
            else -> {
                navigationType = PlaygroundNavigationType.BOTTOM_NAVIGATION
                contentType = PlaygroundContentType.SINGLE_PANE
            }
        }

        /**
         * Content inside Navigation Rail/Drawer can also be positioned at top, bottom or center for
         * ergonomics and reachability depending upon the height of the device.
         */
        val navigationContentPosition = when (windowSize.heightSizeClass) {
            WindowHeightSizeClass.Compact -> {
                PlaygroundNavigationContentPosition.TOP
            }
            WindowHeightSizeClass.Medium,
            WindowHeightSizeClass.Expanded -> {
                PlaygroundNavigationContentPosition.CENTER
            }
            else -> {
                PlaygroundNavigationContentPosition.TOP
            }
        }

//        val navigationType = getNavigationType(windowSize.widthSizeClass, devicePosture)
        TomsPlaygroundNavigationWrapper(navigationType, contentType)

    }
}

fun getNavigationType(windowSize: WindowWidthSizeClass, devicePosture: DevicePosture): Any {
    return when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            PlaygroundNavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            PlaygroundNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            if (devicePosture is DevicePosture.BookPosture) {
                PlaygroundNavigationType.NAVIGATION_RAIL
            } else {
                PlaygroundNavigationType.PERMANENT_NAVIGATION_DRAWER
            }
        }
        else -> {
            PlaygroundNavigationType.BOTTOM_NAVIGATION
        }
    }
}

@Composable
fun TomsPlaygroundNavigationWrapper(
    navigationType: PlaygroundNavigationType,
    contentType: PlaygroundContentType
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: HOME_SCREEN

    if (navigationType == PlaygroundNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PlaygroundPermanentNavigationDrawerView(navController)
    } else {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == PlaygroundNavigationType.NAVIGATION_RAIL) {
                PlaygroundNavigationRail(
                    navController
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                TomsPlaygroundNavGraph(Modifier.weight(1f))
                AnimatedVisibility(visible = navigationType == PlaygroundNavigationType.BOTTOM_NAVIGATION) {
                    PlaygroundNavigationBar(
                        navController
                    )
                }
            }
        }
    }
}

@Composable
fun PlaygroundAppContent() {
    Text(text = "test")
}


object NavContent {
    val navItems = listOf(
        NavComponentItem("Home", HOME_SCREEN, Icons.Default.Home, R.string.home_icon_content_desc),
        NavComponentItem(
            "Search",
            TomsPlaygroundDestinations.SEARCH_SCREEN,
            Icons.Default.Search,
            R.string.search_icon_content_desc
        ),
        NavComponentItem(
            "Post",
            TomsPlaygroundDestinations.POST_SCREEN,
            Icons.Default.Add,
            R.string.post_icon_content_desc
        ),
        NavComponentItem(
            "Profile",
            TomsPlaygroundDestinations.PROFILE_SCREEN,
            Icons.Default.Person,
            R.string.person_icon_content_desc
        )
    )
}


@Composable
fun PlaygroundNavigationRail(navController: NavController) {
    val currentDestination = navController.currentDestination?.navigatorName
    NavigationRail(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        navItems.forEach {
            NavigationRailItem(
                selected = currentDestination == it.location,
                onClick = { navController.navigate(it.location) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = stringResource(id = it.contentDescription)
                    )
                },
                label = {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}

@Composable
fun PlaygroundNavigationBar(navController: NavController) {
    val currentDestination = navController.currentDestination?.navigatorName
    BottomAppBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        navItems.forEach {
            NavigationBarItem(
                selected = currentDestination == it.location,
                onClick = { navController.navigate(it.location) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = stringResource(id = it.contentDescription)
                    )
                },
                label = {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundPermanentNavigationDrawerView(navController: NavController) {
    val currentDestination = navController.currentDestination?.navigatorName
    PermanentNavigationDrawer(drawerContent = { PlaygroundNavigationDrawerContent(navController) }) {
        TomsPlaygroundNavGraph()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundNavigationDrawerContent(navController: NavController) {
    val currentDestination = navController.currentDestination?.navigatorName
    PermanentDrawerSheet(modifier = Modifier.sizeIn(minWidth = 150.dp, maxWidth = 200.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            navItems.forEach {
                NavigationDrawerItem(
                    selected = currentDestination == it.location,
                    onClick = { navController.navigate(it.location) },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = stringResource(id = it.contentDescription)
                        )
                    },
                    label = {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Center
                        )
                    })
            }
        }
    }
}

data class NavComponentItem(
    val name: String,
    val location: String,
    val icon: ImageVector,
    val contentDescription: Int
)

