package com.dicoding.asclepius.view.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.data.NewsRepository
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import kotlinx.coroutines.launch

class HealthNewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _news = MutableLiveData<List<ArticlesItem?>?>()
    val news: LiveData<List<ArticlesItem?>?> get() = _news

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchPosts() {
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val responseBody = repository.fetchCancerNews()
                val newsList = responseBody.articles
                val filteredList = newsList?.filter { article ->
                    article?.title != "[Removed]"
                }
                _news.value = filteredList
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Failed to load data: ${e.message}"
            }
        }
    }
}