package com.example.githubapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.response.GithubResponse
import com.example.githubapp.data.response.ItemsItem
import com.example.githubapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val apiService = ApiConfig.getApiService()
    private val userListLiveData = MutableLiveData<List<ItemsItem>>()

    init {
        searchUsers("daffa")
    }

    fun searchUsers(query: String) {
        apiService.getGithubSearch(query).enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                handleResponse(response)
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}", t)
            }
        })
    }

    private fun handleResponse(response: Response<GithubResponse>) {
        if (response.isSuccessful) {
            val items = response.body()?.items
            userListLiveData.value = items ?: emptyList()
        } else {
            Log.e(TAG, "Error response body: ${response.errorBody()?.string()}")
        }
    }

    fun getUsersLiveData(): LiveData<List<ItemsItem>> {
        return userListLiveData
    }
}