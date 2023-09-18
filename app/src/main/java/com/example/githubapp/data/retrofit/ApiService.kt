package com.example.githubapp.data.retrofit

import com.example.githubapp.data.response.DetailUserResponse
import com.example.githubapp.data.response.GithubResponse
import com.example.githubapp.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun getGithubSearch(
        @Query("q") query: String
    ): Call<GithubResponse>
    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String
    ): Call<DetailUserResponse>
    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String
    ): Call<List<ItemsItem>>
    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String
    ): Call<List<ItemsItem>>
}