package com.tomsplayground.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column() {
        UserPostFeed(
            "https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg",
            "Tom Rowbotham",
            "10/10 Great Meal",
            contentType

        )
    }
}

@Preview(showBackground = true, widthDp = 1100, heightDp = 600)
@Composable
fun DefaultPreview() {
    TomsPlaygroundTheme {
        Column() {
            UserPostFeed(
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
    fullName: String,
    caption: String,
    contentType: PlaygroundContentType
) {
    val pfpModifier = Modifier
        .padding(8.dp)
        .size(24.dp)
        .clip(CircleShape)
        .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
    LazyColumn(Modifier) {
        item {
            Post(fullName, pfpModifier, caption, contentType)
        }
    }
}

@Composable
private fun Post(
    fullName: String,
    pfpModifier: Modifier,
    caption: String,
    contentType: PlaygroundContentType
) {
    PostHeader(fullName, pfpModifier)
    if (contentType == PlaygroundContentType.SINGLE_PANE) {
        Image(
            painter = painterResource(R.drawable.default_profile_pic),
            contentDescription = "",
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1f)

        )
        PostCaption(fullName, caption)
    } else {
        Row{
            Image(
                painter = painterResource(R.drawable.default_profile_pic),
                contentDescription = "",
                Modifier
                    .width(300.dp)
                    .aspectRatio(1f / 1f)

            )
            PostCaption(fullName, caption)
        }
    }
    Divider()
}

@Composable
private fun PostCaption(fullName: String, caption: String) {
    Column {
        Text(
            text = fullName,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp)
        )
        Text(
            text = caption,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
private fun PostHeader(fullName: String, pfpModifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.default_profile_pic),
            contentDescription = "$fullName Profile Picture",
            modifier = pfpModifier,
            contentScale = ContentScale.Crop,
        )
        Text(
            text = fullName,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}