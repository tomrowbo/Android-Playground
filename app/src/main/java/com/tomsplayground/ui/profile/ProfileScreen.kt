package com.tomsplayground.ui.profile

import android.content.res.Configuration
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
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tomsplayground.R
import com.tomsplayground.TomsPlaygroundApp
import com.tomsplayground.ui.theme.TomsPlaygroundTheme

@Preview(showSystemUi = true)
@Composable
fun ProfileScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        PhotoGrid(
            listOf(
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574",
                "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg",
            )
        )
    }

}

@Composable
fun PhotoGrid(photoUrls: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        item(span = {GridItemSpan(maxLineSpan)}){
            val pfpModifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg")
                        .crossfade(true).error(R.drawable.default_profile_pic)
                        .build(),
                    contentDescription = "Profile Picture",
                    modifier = pfpModifier,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = "Tom Rowbotham",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "@tom.rowbo",
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


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun PlaygroundAppPreview() {
    TomsPlaygroundTheme {
        ProfileScreen()
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    showBackground = true,
    widthDp = 700,
    heightDp = 500,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PlaygroundAppPreviewTablet() {
    TomsPlaygroundTheme {
        ProfileScreen()
    }
}

@Preview(showBackground = true, widthDp = 500, heightDp = 700)
@Composable
fun PlaygroundAppPreviewTabletPortrait() {
    TomsPlaygroundTheme {
        ProfileScreen()
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 1100, heightDp = 600)
@Composable
fun PlaygroundAppPreviewDesktop() {
    TomsPlaygroundTheme {
        ProfileScreen()
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 600, heightDp = 1100)
@Composable
fun PlaygroundAppPreviewDesktopPortrait() {
    TomsPlaygroundTheme {
        ProfileScreen()
    }
}

