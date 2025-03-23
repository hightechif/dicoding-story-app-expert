package com.fadhil.storyappexpert.core.data.source.local

import com.fadhil.storyappexpert.core.data.source.local.db.FavoritesDao
import com.fadhil.storyappexpert.core.data.source.local.entity.FavoriteEntity
import javax.inject.Inject

class FavoritesLocalDataSource @Inject constructor(
    private val dao: FavoritesDao
) {

    suspend fun addFavorite(favorite: FavoriteEntity) = dao.addFavorite(favorite)
    fun getFavorites() = dao.getFavorites()
    suspend fun update(favorite: FavoriteEntity) = dao.update(favorite)
    suspend fun deleteAll() = dao.deleteAll()

}