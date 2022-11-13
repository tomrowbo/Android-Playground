package com.tomsplayground.domain.mapper

import com.tomsplayground.domain.model.Post
import com.tomsplayground.ui.home.PostUiModel
import javax.inject.Inject

class PostUiStateMapper @Inject constructor(){
    fun toPostUiModel(post: Post): PostUiModel{
        return PostUiModel(
            postAuthor = post.postAuthor,
            postAuthorPfp = post.authorPfp,
            imageUrl = post.imageUrl,
            contentDesc = post.imageContentDesc,
            caption = post.caption
        )
    }
}