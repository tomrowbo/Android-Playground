package com.tomsplayground.domain.model

data class Post(
    val postAuthor: String,
    // TODO: Remove from post and store in user class or store user object here
    val authorPfp: String?,
    val id: String,
    val imageUrl: String? = null,
    val imageContentDesc: String? = null,
    val caption: String? = null,
)
