package com.example.githubapp.data.repository

import androidx.lifecycle.LiveData
import com.example.githubapp.data.database.FavoriteUser
import com.example.githubapp.data.database.FavoriteUserDao
import com.example.githubapp.utils.AppExecutors

class FavoriteRepository private constructor(
    private val databaseDao: FavoriteUserDao,
    private val appExecutors: AppExecutors
) {
    fun isFavorited(username: String): LiveData<FavoriteUser> =
        databaseDao.isFavorite(username)

    fun getAllFavorite(): LiveData<List<FavoriteUser>> = databaseDao.getAllFavorite()
    fun insertFavUser(favoriteUser: FavoriteUser) {
        appExecutors.diskIO.execute { databaseDao.insert(favoriteUser) }
    }

    fun deleteFavUser(username: String) {
        appExecutors.diskIO.execute { databaseDao.delete(username) }
    }

    companion object {
        @Volatile
        private var instance: FavoriteRepository? = null
        fun getInstance(
            databaseDao: FavoriteUserDao,
            appExecutors: AppExecutors
        ): FavoriteRepository =
            instance ?: synchronized(this) {
                instance ?: FavoriteRepository(databaseDao, appExecutors)
            }.also { instance = it }
    }
}