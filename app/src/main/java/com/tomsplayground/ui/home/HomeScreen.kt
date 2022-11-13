package com.tomsplayground.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tomsplayground.R
import com.tomsplayground.ui.theme.TomsPlaygroundTheme
import com.tomsplayground.ui.utils.PlaygroundContentType

@Composable
fun HomeScreen(viewModel: HomeViewModel, contentType: PlaygroundContentType) {
    val uiState by viewModel.uiState.collectAsState()
    HomeContent(uiState, contentType)
}

@Composable
fun HomeContent(uiState: HomeUiState, contentType: PlaygroundContentType) {
    LazyColumn(Modifier) {
        items(uiState.posts) { post ->
            Post(post.postAuthor, post.imageUrl, post.caption, post.postAuthorPfp, contentType)
        }
    }
}

@Preview(showBackground = true, widthDp = 1100, heightDp = 600)
@Composable
fun DefaultPreview() {
    TomsPlaygroundTheme {
        Column() {
            UserPostFeed(
                "https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg",
                "https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg",
                "Tom Rowbotham",
                "10/10 Great Meal",
                PlaygroundContentType.DUAL_PANE
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreviewPhone() {
    TomsPlaygroundTheme {
        Column() {
            UserPostFeed(
                "https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg",
                "https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg",
                "Tom Rowbotham",
                "10/10 Great Meal",
                PlaygroundContentType.SINGLE_PANE
            )
        }
    }
}

@Composable
fun UserPostFeed(
    pfpUrl: String,
    imageUrl: String,
    fullName: String,
    caption: String,
    contentType: PlaygroundContentType
) {
    LazyColumn(Modifier) {

        item {
            Post(fullName, imageUrl, caption, pfpUrl, contentType)
        }
    }
}

@Composable
private fun Post(
    fullName: String?,
    imageUrl: String?,
    caption: String?,
    pfpUrl: String?,
    contentType: PlaygroundContentType
) {
    PostHeader(pfpUrl, fullName)
    if (contentType == PlaygroundContentType.SINGLE_PANE) {
        //TODO: Once loaded should store pfps locally not URL
        AsyncImage(
            model  = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true).error(R.drawable.default_profile_pic)
                .build(),
            contentDescription = "${fullName} profile picture",
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1f),
            contentScale = ContentScale.Crop
        )
        PostCaption(fullName, caption)
    } else {
        Row{
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true).error(R.drawable.default_profile_pic)
                    .build(),
                contentDescription = "Picture",
                modifier = Modifier.width(300.dp).height(300.dp),
                contentScale = ContentScale.Crop)
            PostCaption(fullName, caption)
        }
    }
    Divider()
}

@Composable
private fun PostCaption(fullName: String?, caption: String?) {
    Column {
        Text(
            text = fullName?: "Unknown User",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp)
        )
        Text(
            text = caption ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
private fun PostHeader(pfpUrl: String?, fullName: String?) {
    val pfpModifier = Modifier
        .padding(8.dp)
        .size(24.dp)
        .clip(CircleShape)
        .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
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
            text = fullName?: "Unknown User",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}