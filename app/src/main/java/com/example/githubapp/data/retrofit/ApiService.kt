package com.example.githubapp.data.retrofit

import com.example.githubapp.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun getGithubSearch(
        @Query("q") query: String
    ): Call<GithubResponse>
}