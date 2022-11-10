package com.tomsplayground.ui.home

data class HomeUiState(
    var posts: List<ExpandedPostUiModel> = emptyList(),
)

data class ExpandedPostUiModel(
    val postAuthor: String? = null,
    val imageUrl: String? = null,
    val caption: String? = null
)