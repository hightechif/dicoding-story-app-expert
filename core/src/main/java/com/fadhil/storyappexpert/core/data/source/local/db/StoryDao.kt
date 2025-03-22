package com.fadhil.storyappexpert.core.data.source.local.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fadhil.storyappexpert.core.data.source.local.entity.StoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {

    @Insert(entity = StoryEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(stories: List<StoryEntity>)

    @Query(
        "SELECT s.id, s.name, s.description, s.photoUrl, " +
                "s.created_time, s.lat, s.lon, s.favorite " +
                "FROM stories s " +
                "ORDER BY created_time " +
                "DESC"
    )
    fun getStories(): Flow<List<StoryEntity>>

    @Query(
        "SELECT s.id, s.name, s.description, s.photoUrl, " +
                "s.created_time, s.lat, s.lon, s.favorite " +
                "FROM stories s " +
                "ORDER BY created_time " +
                "DESC"
    )
    fun getPagingStories(): PagingSource<Int, StoryEntity>

    @Update(StoryEntity::class, OnConflictStrategy.REPLACE)
    suspend fun updateData(story: StoryEntity)

    @Query(
        "SELECT s.id, s.name, s.description, s.photoUrl, " +
                "s.created_time, s.lat, s.lon, s.favorite " +
                "FROM stories s " +
                "WHERE favorite == 1 " +
                "ORDER BY created_time " +
                "DESC"
    )
    fun getFavorites(): Flow<List<StoryEntity>>

    @Query("DELETE FROM stories")
    suspend fun deleteAll()

}