package com.tomsplayground.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomsplayground.data.repository.PostRepository
import com.tomsplayground.domain.mapper.PostUiStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val postUiStateMapper: PostUiStateMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getContent()
    }

    private fun getContent() {
        viewModelScope.launch {
            val posts = postRepository.getUserFeedPosts()
            val userFeed = posts.map { postUiStateMapper.toPostUiModel(it) }
            _uiState.value = _uiState.value.copy(posts = userFeed)
        }
    }

}
