package com.tomsplayground

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.tomsplayground.TomsPlaygroundDestinations.HOME_SCREEN
import com.tomsplayground.ui.navigation.PlaygroundNavigationBar
import com.tomsplayground.ui.navigation.PlaygroundNavigationDrawer
import com.tomsplayground.ui.navigation.PlaygroundNavigationRail
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
        PlaygroundNavigationDrawer()
    } else {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == PlaygroundNavigationType.NAVIGATION_RAIL) {
                PlaygroundNavigationRail(
                    navController,
                    selectedDestination,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                AnimatedVisibility(visible = navigationType == PlaygroundNavigationType.BOTTOM_NAVIGATION) {
                    PlaygroundNavigationBar(
                        selectedDestination = selectedDestination,
                    )
                }
            }
        }
    }
}

@Composable
fun PlaygroundAppContent(navigationType: PlaygroundNavigationType) {
    TODO("Not yet implemented")
}

