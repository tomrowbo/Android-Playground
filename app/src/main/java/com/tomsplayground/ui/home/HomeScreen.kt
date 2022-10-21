package com.tomsplayground.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.tomsplayground.ui.theme.TomsPlaygroundTheme


@Composable
fun HomeScreen(viewModel: HomeViewModel){
    val uiState by viewModel.uiState.collectAsState()
    HomeContent(uiState)
}

@Composable
fun HomeContent(uiState: HomeUiState) {
    Text(uiState.welcomeMessage ?: "")
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TomsPlaygroundTheme {

    }
}