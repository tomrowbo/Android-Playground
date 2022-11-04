package com.tomsplayground.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tomsplayground.R

@Composable
fun ProfileScreen(widthSize: WindowWidthSizeClass, viewModel: ProfileViewModel) {
    val columns = when (widthSize) {
        WindowWidthSizeClass.Compact -> 3
        WindowWidthSizeClass.Medium -> 4
        WindowWidthSizeClass.Expanded -> 5
        else -> 1
    }

    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        PhotoGrid(
            uiState.postUrls, uiState.username, uiState.name, uiState.profilePicUrl,columns
        )
    }

}

@Composable
fun PhotoGrid(photoUrls: List<String>, username: String?, fullName: String?, pfpUrl: String?,columns: Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        item(span = {GridItemSpan(maxLineSpan)}){
            val pfpModifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
            Column(Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pfpUrl)
                        .crossfade(true).error(R.drawable.default_profile_pic)
                        .build(),
                    contentDescription = "$fullName Profile Picture",
                    modifier = pfpModifier,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = fullName?:"",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "@$username",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        items(photoUrls) { photoUrl ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoUrl)
                    .crossfade(true).error(R.drawable.default_profile_pic)
                    .build(),
                contentDescription = "Picture",
                modifier = Modifier.fillMaxSize().aspectRatio(1f/1f).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

