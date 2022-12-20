package com.tomsplayground.data.repository

import com.tomsplayground.data.dao.PostDao
import com.tomsplayground.data.model.PostEntity
import javax.inject.Inject

class PostDatastore @Inject constructor(
    private val postDao: PostDao
) {

    suspend fun createPost(post: PostEntity){
        postDao.insertAll(post)
    }
}