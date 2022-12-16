package com.tomsplayground.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tomsplayground.data.dao.PostDao
import com.tomsplayground.data.model.PostEntity

@Database(
    entities = [
        PostEntity::class,
    ],
    version = 1
)
abstract class TomsPlaygroundDb : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        private const val DB_NAME = "toms-playground-db"

        @Volatile
        private var INSTANCE: TomsPlaygroundDb? = null

        fun getDatabase(context: Context): TomsPlaygroundDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TomsPlaygroundDb::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}