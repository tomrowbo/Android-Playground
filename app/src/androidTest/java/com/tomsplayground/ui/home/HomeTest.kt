package com.tomsplayground.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.tomsplayground.domain.model.Post
import com.tomsplayground.ui.theme.TomsPlaygroundTheme
import com.tomsplayground.ui.utils.PlaygroundContentType
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class HomeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeTest() {
        // Start the app
        composeTestRule.setContent {
            val mockUiState = remember {mutableStateOf(HomeUiState(getMockedPostsUiState()))}
            TomsPlaygroundTheme() {
                HomeScreen(mockUiState,
                    PlaygroundContentType.SINGLE_PANE
                )
            }
        }
        sleep(10000)
        composeTestRule.onNode(hasText("post_author")).assertIsDisplayed()
        composeTestRule.onNode(hasText("caption")).assertIsDisplayed()
    }

    private fun getMockedPostsUiState(): List<PostUiModel> {
        return listOf(
            PostUiModel(
                POST_AUTHOR,
                AUTHOR_PFP,
                IMAGE_URL,
                CONTENT_DESC,
                CAPTION
            ),
            PostUiModel(
                SECOND_POST_AUTHOR,
                AUTHOR_PFP,
                IMAGE_URL,
                CONTENT_DESC,
                CAPTION2
            )
        )
    }

    companion object {
        const val POST_AUTHOR = "post_author"
        const val AUTHOR_PFP = "author_pfp"
        const val IMAGE_URL = "image_url"
        const val CONTENT_DESC = "content_desc"
        const val CAPTION = "caption"
        const val CAPTION2 = "caption2"
        const val SECOND_POST_AUTHOR = "second_post_author"
    }

}