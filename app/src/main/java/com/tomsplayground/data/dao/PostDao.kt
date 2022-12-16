package com.tomsplayground.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tomsplayground.data.model.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getAll(): LiveData<List<PostEntity>>

    @Insert
    fun insertAll(vararg posts: PostEntity)
}