package com.example.githubapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.response.DetailUserResponse
import com.example.githubapp.data.response.ItemsItem
import com.example.githubapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()

    private val _userDetailLiveData = MutableLiveData<DetailUserResponse>()
    val userDetailLiveData: LiveData<DetailUserResponse> = _userDetailLiveData

    private val _followersLiveData = MutableLiveData<List<ItemsItem>>()
    val followersLiveData: LiveData<List<ItemsItem>> = _followersLiveData

    private val _followingLiveData = MutableLiveData<List<ItemsItem>>()
    val followingLiveData: LiveData<List<ItemsItem>> = _followingLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getUserDetail(username: String) {
        apiService.getDetailUser(username).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    _userDetailLiveData.value = response.body()
                } else {
                    _errorLiveData.value = "Gagal memuat detail pengguna"
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _errorLiveData.value = "Gagal memuat"
            }
        })
    }

    fun getFollowers(username: String) {
        apiService.getFollowers(username).enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if (response.isSuccessful) {
                    _followersLiveData.value = response.body()
                } else {
                    _errorLiveData.value = "Gagal memuat daftar Followers"
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _errorLiveData.value = "Gagal memuat daftar Followers"
            }
        })
    }
    
    fun getFollowing(username: String) {
        apiService.getFollowing(username).enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if (response.isSuccessful) {
                    _followingLiveData.value = response.body()
                } else {
                    _errorLiveData.value = "Gagal memuat daftar Following"
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _errorLiveData.value = "Gagal memuat daftar Following"
            }
        })
    }
}