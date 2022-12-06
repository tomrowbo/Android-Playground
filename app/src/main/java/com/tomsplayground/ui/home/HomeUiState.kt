package com.tomsplayground.ui.home

data class HomeUiState(
    var posts: List<PostUiModel> = emptyList(),
)

data class PostUiModel(
    val postAuthor: String? = null,
    val postAuthorPfp: String? = null,
    val imageUrl: String? = null,
    val contentDesc: String? = null,
    val caption: String? = null
)
