package com.tomsplayground.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tomsplayground.ui.theme.TomsPlaygroundTheme


@Composable
fun HomeRoute(){
    Greeting(name = "Tom")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TomsPlaygroundTheme {
        Greeting("Android")
    }
}