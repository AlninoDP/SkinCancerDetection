package com.dicoding.asclepius.data.remote.retrofit

import com.dicoding.asclepius.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getCancerNews(
        @Query("q")q: String? = "cancer",
        @Query("category") category: String? = "health",
        @Query("language") language: String? = "en",
        @Query("apiKey") apiKey: String? = null
    ) : NewsResponse
}