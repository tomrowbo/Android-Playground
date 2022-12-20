package com.tomsplayground.data.database

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.tomsplayground.data.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context
    ): TomsPlaygroundDb {
        return TomsPlaygroundDb.getDatabase(context)
    }

    @Singleton
    @Provides
    fun providesPostDao(db: TomsPlaygroundDb): PostDao = db.postDao()

}