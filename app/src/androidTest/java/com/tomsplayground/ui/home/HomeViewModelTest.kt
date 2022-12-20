package com.tomsplayground.ui.home

import com.google.common.truth.Truth
import com.tomsplayground.data.repository.PostRepository
import com.tomsplayground.domain.mapper.PostUiStateMapper
import com.tomsplayground.domain.model.Post
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    private val postUiStateMapper = PostUiStateMapper()

    private val postRepository = mockk<PostRepository>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun GIVEN_postsFromRepository_WHEN_getContent_THEN_shouldMapAsPostsUiState() = runTest {
        val posts = getMockPosts()
        val userFeed = getMappedPostsUiState()
        every { postRepository.getUserFeedPosts() } returns posts

        generateViewModel()

        Truth.assertThat(userFeed).isEqualTo(homeViewModel.uiState.value.posts)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun generateViewModel() {
        homeViewModel = HomeViewModel(postRepository, postUiStateMapper)
    }

    private fun getMockPosts(): List<Post> {
        val post1 = Post(
            POST_AUTHOR, AUTHOR_PFP, "1", IMAGE_URL, CONTENT_DESC, CAPTION
        )
        val post2 = Post(
            SECOND_POST_AUTHOR, AUTHOR_PFP, "2", IMAGE_URL, CONTENT_DESC, CAPTION
        )
        return listOf(post1, post2)
    }

    private fun getMappedPostsUiState(): List<PostUiModel> {
        return listOf(
            PostUiModel(POST_AUTHOR, AUTHOR_PFP, IMAGE_URL, CONTENT_DESC, CAPTION),
            PostUiModel(SECOND_POST_AUTHOR, AUTHOR_PFP, IMAGE_URL, CONTENT_DESC, CAPTION)
        )
    }

    companion object {
        const val POST_AUTHOR = "post_author"
        const val AUTHOR_PFP = "author_pfp"
        const val IMAGE_URL = "image_url"
        const val CONTENT_DESC = "content_desc"
        const val CAPTION = "caption"
        const val SECOND_POST_AUTHOR = "second_post_author"
    }
}
