package com.tomsplayground.ui.home

import com.tomsplayground.domain.PostRepository
import com.tomsplayground.domain.mapper.PostUiStateMapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test


class HomeViewModelTest{

    lateinit var homeViewModel: HomeViewModel

    private val postUiStateMapper = PostUiStateMapper()

    @MockK
    private val postRepository: PostRepository = mockk()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(postRepository, postUiStateMapper)
    }

    @Test
    fun GIVEN_postsFromRepository_WHEN_getContent_THEN_shouldMapAsPostsUiState(){

    }


    @After
    fun tearDown() {
        unmockkAll()
    }
}