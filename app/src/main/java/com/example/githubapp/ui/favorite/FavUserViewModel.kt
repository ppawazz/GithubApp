package com.example.githubapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.database.FavoriteUser
import com.example.githubapp.data.repository.FavoriteRepository

class FavUserViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    fun getAllFavorite() :LiveData<List<FavoriteUser>> = favoriteRepository.getAllFavorite()
    fun insertFavUser(favoriteUser: FavoriteUser) {
        favoriteRepository.insertFavUser(favoriteUser)
    }
    fun updateFavUser(favoriteUser: FavoriteUser) {
        favoriteRepository.updateFavUser(favoriteUser)
    }
    fun deleteFavUser(favoriteUser: FavoriteUser) {
        favoriteRepository.deleteFavUser(favoriteUser)
    }
}
