package com.tomsplayground.domain.model

data class User(
    val username: String,
    val name: String,
    val pfpUrl: String? = null,
    val postIds: List<String> = emptyList()
)
