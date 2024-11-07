package com.dicoding.asclepius.data


import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.data.remote.response.NewsResponse
import com.dicoding.asclepius.data.remote.retrofit.ApiConfig

class NewsRepository {
    private val apiService = ApiConfig().getApiService()


    suspend fun fetchCancerNews(): NewsResponse {
        return apiService.getCancerNews(apiKey = BuildConfig.API_KEY)
    }
}