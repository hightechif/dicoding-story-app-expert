package com.fadhil.storyappexpert.core.data.source.local.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fadhil.storyappexpert.core.data.source.local.entity.RemoteKeys
import com.fadhil.storyappexpert.core.data.source.local.entity.StoryEntity

@androidx.room.Database(
    entities = [StoryEntity::class, RemoteKeys::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        const val DATABASE_NAME = "AppDB"

        fun getDatabase(context: Context, dbName: String): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    dbName
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}