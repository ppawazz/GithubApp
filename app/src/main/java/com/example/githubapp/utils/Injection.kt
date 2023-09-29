package com.example.githubapp.utils

import android.content.Context
import com.example.githubapp.data.database.FavUserDatabase
import com.example.githubapp.data.repository.FavoriteRepository
import com.example.githubapp.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): FavoriteRepository {
        val database = FavUserDatabase.getDatabase(context)
        val dao = database.favUserDao()
        val appExecutors = AppExecutors()
        return FavoriteRepository.getInstance(dao, appExecutors)
    }
}