package com.tomsplayground.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tomsplayground.TOP_LEVEL_NAV_DESTINATION_ITEMS

@Composable
fun PlaygroundNavigationRail(
    navigateToTopLevelDestination: (NavComponentItem) -> Unit,
    selectedDestination: String
) {
    NavigationRail(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        TOP_LEVEL_NAV_DESTINATION_ITEMS.forEach {
            NavigationRailItem(
                selected = selectedDestination == it.location,
                onClick = { navigateToTopLevelDestination(it) },
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
fun PlaygroundNavigationBar(
    navigateToTopLevelDestination: (NavComponentItem) -> Unit,
    selectedDestination: String
) {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        TOP_LEVEL_NAV_DESTINATION_ITEMS.forEach {
            NavigationBarItem(
                selected = selectedDestination == it.location,
                onClick = { navigateToTopLevelDestination(it) },
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
fun PlaygroundPermanentNavigationDrawerView(
    widthSize: WindowWidthSizeClass,
    navigateToTopLevelDestination: (NavComponentItem) -> Unit,
    selectedDestination: String
) {
    PermanentNavigationDrawer(drawerContent = {
        PlaygroundNavigationDrawerContent(
            navigateToTopLevelDestination,
            selectedDestination
        )
    }) {
        TomsPlaygroundNavGraph(widthSize = widthSize)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundNavigationDrawerContent(
    navigateToTopLevelDestination: (NavComponentItem) -> Unit,
    selectedDestination: String
) {
    PermanentDrawerSheet(modifier = Modifier.sizeIn(minWidth = 150.dp, maxWidth = 200.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TOP_LEVEL_NAV_DESTINATION_ITEMS.forEach {
                NavigationDrawerItem(
                    selected = selectedDestination == it.location,
                    onClick = { navigateToTopLevelDestination(it) },
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

