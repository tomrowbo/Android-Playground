package com.tomsplayground.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tomsplayground.R

@Preview(showSystemUi = true)
@Composable
fun ProfileScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val imageModifier = Modifier.size(120.dp).clip(CircleShape)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://w0.peakpx.com/wallpaper/979/89/HD-wallpaper-purple-smile-design-eye-smily-profile-pic-face.jpg",)
                .crossfade(true).error(R.drawable.default_profile_pic)
                .build(),
            contentDescription = "Profile Picture",
        modifier = imageModifier,
        contentScale = ContentScale.Crop)
        Text(text = "Tom Rowbotham", style = MaterialTheme.typography.titleLarge)
    }
}