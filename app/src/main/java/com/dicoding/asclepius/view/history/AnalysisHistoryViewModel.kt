package com.dicoding.asclepius.view.history

import android.content.Context
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.AnalysisHistoryRepository

class AnalysisHistoryViewModel(context: Context) : ViewModel() {
    private val repository = AnalysisHistoryRepository(context)


    fun getAllHistory() = repository.getAllHistory()

    suspend fun deleteAllHistory() = repository.deleteAllHistory()

    suspend fun deleteHistoryByImageUri(imageUri: String) = repository.deleteHistoryByImageUri(imageUri)


}