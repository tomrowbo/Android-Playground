package com.tomsplayground

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.tomsplayground.ui.navigation.PlaygroundNavigationBar
import com.tomsplayground.ui.navigation.PlaygroundNavigationRail
import com.tomsplayground.ui.navigation.PlaygroundPermanentNavigationDrawerView
import com.tomsplayground.ui.navigation.TomsPlaygroundNavGraph
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

        TomsPlaygroundNavigationWrapper(navigationType, contentType, windowSize.widthSizeClass)
    }
}

@Composable
fun TomsPlaygroundNavigationWrapper(
    navigationType: PlaygroundNavigationType,
    contentType: PlaygroundContentType,
    widthSize: WindowWidthSizeClass
) {
    val navController = rememberNavController()
    val topLevelNavActions = remember(navController) {
        TomsPlaygroundNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: TomsPlaygroundTopLevelDestinations.HOME_SCREEN

    if (navigationType == PlaygroundNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PlaygroundPermanentNavigationDrawerView(
            widthSize,
            topLevelNavActions::navigateTo,
            selectedDestination,
            navController
        )
    } else {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == PlaygroundNavigationType.NAVIGATION_RAIL) {
                PlaygroundNavigationRail(
                    topLevelNavActions::navigateTo, selectedDestination
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                TomsPlaygroundNavGraph(Modifier.weight(1f), navController, widthSize = widthSize)
                AnimatedVisibility(visible = navigationType == PlaygroundNavigationType.BOTTOM_NAVIGATION) {
                    PlaygroundNavigationBar(
                        topLevelNavActions::navigateTo, selectedDestination
                    )
                }
            }
        }
    }
}
