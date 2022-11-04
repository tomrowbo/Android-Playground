package com.tomsplayground.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomsplayground.domain.PostRepository
import com.tomsplayground.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(loading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadContent("tom.rowbo")
    }

    private fun loadContent(username: String?) {
        viewModelScope.launch {
            val user = userRepository.findUserByUsername(username)
            _uiState.value = _uiState.value.copy(
                username = username,
                name = user.name,
                profilePicUrl = user.pfpUrl
            )
            val posts = postRepository.getPostsById(user.postIds)
            _uiState.value = _uiState.value.copy(
                postUrls = posts.map { it.imageUrl ?: "" }
            )
        }
    }

}