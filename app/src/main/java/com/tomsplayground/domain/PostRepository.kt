package com.tomsplayground.domain

import com.tomsplayground.domain.model.Post
import javax.inject.Inject

class PostRepository @Inject constructor() {
    fun getPostById() {
    }

    fun getPostsById(postIds: List<String>): List<Post> {
        return listOf(
            post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2,
            post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2,
        )
    }

    fun getUserFeedPosts(): List<Post>{
        return listOf(
            post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2,
            post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2, post1, post2,
        )
    }

    // TODO Remove once added data layer
    val post1 = Post("tom.rowbo","1", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=768,574","", "Good food")
    val post2 = Post("tom.rowbo","2", "https://realfood.tesco.com/media/images/472x310-SausageRaguGnocchi-4921325c-64d5-4973-9451-759d0c115e2e-0-472x310.jpg","", "Excellent food")
}
