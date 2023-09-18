package com.example.githubapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.response.DetailUserResponse
import com.example.githubapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()

    private val _userDetailLiveData = MutableLiveData<DetailUserResponse>()
    val userDetailLiveData: LiveData<DetailUserResponse> = _userDetailLiveData

    fun getUserDetail(username: String) {
        apiService.getDetailUser(username).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    _userDetailLiveData.value = response.body()
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}