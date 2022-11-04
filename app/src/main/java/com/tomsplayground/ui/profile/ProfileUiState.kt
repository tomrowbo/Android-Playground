package com.tomsplayground.ui.profile

data class ProfileUiState(
    var name: String? = null,
    var profilePicUrl: String? = null,
    var username: String? = null,
    var postUrls: List<String> = emptyList(),
    var loading: Boolean = false,
    val error: String? = null
)
