package com.tomsplayground.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tomsplayground.data.model.PostColumns.ID
import com.tomsplayground.data.model.PostColumns.POST_URL

@Entity(tableName = "post")
data class PostEntity(
    @ColumnInfo(name = ID) @PrimaryKey(autoGenerate = true)
    val id: String,

    @ColumnInfo(name = POST_URL)
    val postUrl: String
)

object PostColumns {
    const val ID = "id"
    const val POST_URL = "post_url"
}