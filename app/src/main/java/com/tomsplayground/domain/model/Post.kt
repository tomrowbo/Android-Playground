package com.tomsplayground.domain.model

data class Post(
    val postOwner: String,
    val id: String,
    val imageUrl: String? = null,
    val imageContentDesc: String? = null,
    val caption: String? = null,
)
