package com.fadhil.storyappexpert.core.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fadhil.storyappexpert.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

@Dao
interface FavoritesDao {

    @Insert(entity = FavoriteEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Query(
        "SELECT f.id, f.story_id, f.created_time, f.favorite " +
                "FROM favorites f " +
                "WHERE favorite == 1 " +
                "ORDER BY created_time " +
                "DESC"
    )
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Query(
        "SELECT f.id, f.story_id, f.created_time, f.favorite " +
                "FROM favorites f " +
                "WHERE f.story_id LIKE :storyId " +
                "LIMIT 1"
    )
    fun getFavorite(storyId: String): Flow<FavoriteEntity>

    @Update(FavoriteEntity::class, OnConflictStrategy.REPLACE)
    suspend fun update(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites")
    suspend fun deleteAll()

}