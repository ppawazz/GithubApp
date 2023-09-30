package com.example.githubapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.database.FavoriteUser
import com.example.githubapp.data.repository.FavoriteRepository

class FavUserViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    fun getAllFavorite(): LiveData<List<FavoriteUser>> = favoriteRepository.getAllFavorite()

    fun isFavorited(username: String): LiveData<FavoriteUser> =
        favoriteRepository.isFavorited(username)

    fun insertFavUser(favoriteUser: FavoriteUser) {
        favoriteRepository.insertFavUser(favoriteUser)
    }

    fun deleteFavUser(username: String) {
        favoriteRepository.deleteFavUser(username)
    }
}
