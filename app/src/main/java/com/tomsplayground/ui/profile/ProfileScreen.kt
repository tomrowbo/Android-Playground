package com.tomsplayground.ui.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tomsplayground.R

@Preview(showSystemUi = true)
@Composable
fun ProfileScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        val pfpModifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colorScheme.onBackground, CircleShape)

        val bannerModifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
        Box() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://media.cntraveller.com/photos/611bedcd231ed5e8dfa34573/16:9/w_2992,h_1683,c_limit/sennen-cove-beach-britain-conde-nast-traveller-20april18-rex.jpg")
                    .crossfade(true).error(R.drawable.default_profile_pic)
                    .build(),
                contentDescription = "Banner Picture",
                modifier = bannerModifier,
                contentScale = ContentScale.Crop,
            )
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
        }
    }

}
