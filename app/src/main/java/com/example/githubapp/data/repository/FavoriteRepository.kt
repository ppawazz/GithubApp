package com.example.githubapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubapp.data.database.FavUserDatabase
import com.example.githubapp.data.database.FavoriteUser
import com.example.githubapp.data.database.FavoriteUserDao
import com.example.githubapp.data.retrofit.ApiService
import com.example.githubapp.utils.AppExecutors
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(
    private val databaseDao: FavoriteUserDao,
    private val executorService: AppExecutors
) {

    fun getAllFavorite(): LiveData<List<FavoriteUser>> = databaseDao.getAllFavorite()
    fun insertFavUser(favoriteUser: FavoriteUser) {
        executorService.diskIO.execute { databaseDao.insert(favoriteUser) }
    }

    fun deleteFavUser(favoriteUser: FavoriteUser) {
        executorService.diskIO.execute { databaseDao.delete(favoriteUser) }
    }

    fun updateFavUser(favoriteUser: FavoriteUser) {
        executorService.diskIO.execute { databaseDao.update(favoriteUser) }
    }

    companion object {
        @Volatile
        private var instance: FavoriteRepository? = null
        fun getInstance(
            databaseDao: FavoriteUserDao,
            executorService: AppExecutors
        ): FavoriteRepository =
            instance ?: synchronized(this) {
                instance ?: FavoriteRepository(databaseDao, executorService)
            }.also { instance = it }
    }
}