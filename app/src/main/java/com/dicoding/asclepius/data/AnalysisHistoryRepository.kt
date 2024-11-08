package com.dicoding.asclepius.data

import android.content.Context
import com.dicoding.asclepius.data.local.entity.AnalysisHistoryEntity
import com.dicoding.asclepius.data.local.room.AnalysisHistoryDatabase

class AnalysisHistoryRepository(context: Context) {
    private val database = AnalysisHistoryDatabase.getInstance(context)
    private val dao = database.analysisHistoryDao()

    fun getAllHistory() = dao.getAllHistory()

    suspend fun insertAnalysisHistory(history: AnalysisHistoryEntity) =
        dao.insertAnalysisHistory(history)

    suspend fun deleteAllHistory() = dao.deleteAllHistory()

    suspend fun deleteHistoryByImageUri(imageUri: String) = dao.deleteHistoryByImageUri(imageUri)

}