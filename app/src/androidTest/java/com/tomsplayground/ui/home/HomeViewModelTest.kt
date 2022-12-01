package com.tomsplayground.ui.home

import com.google.common.truth.Truth
import com.tomsplayground.domain.PostRepository
import com.tomsplayground.domain.mapper.PostUiStateMapper
import com.tomsplayground.domain.model.Post
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    private val postUiStateMapper = PostUiStateMapper()

    @MockK
    private val postRepository: PostRepository = mockk()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    //TODO: Get Init test to pass
//    @Test
//    fun GIVEN_postsFromRepository_WHEN_getContent_THEN_shouldMapAsPostsUiState() = runTest{
//            //GIVEN
//            val posts = getMockPosts()
//            val expectedPosts = getMappedPostsUiState()
//
//            every { postRepository.getUserFeedPosts() } returns posts
//
//            //WHEN
//            generateViewModel()
//
//            //THEN
//            Truth.assertThat(homeViewModel.uiState.value.posts).isEqualTo(expectedPosts)
//
//        }



    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun generateViewModel() {
        homeViewModel = HomeViewModel(postRepository, postUiStateMapper)
    }

    private fun getMockPosts(): List<Post> {
        val post1 = Post(
            POST_AUTHOR,
            AUTHOR_PFP,
            "1",
            IMAGE_URL,
            CONTENT_DESC,
            CAPTION
        )
        val post2 = Post(
            POST_AUTHOR,
            SECOND_POST_AUTHOR,
            "2",
            IMAGE_URL,
            CONTENT_DESC,
            CAPTION
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