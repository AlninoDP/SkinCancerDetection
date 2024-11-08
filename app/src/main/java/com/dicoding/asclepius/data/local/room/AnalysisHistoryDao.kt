package com.dicoding.asclepius.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.asclepius.data.local.entity.AnalysisHistoryEntity

@Dao
interface AnalysisHistoryDao {

    @Query ("SELECT * FROM analysis_history")
    fun getAllHistory(): LiveData<List<AnalysisHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnalysisHistory(history: AnalysisHistoryEntity)

    @Query("DELETE FROM analysis_history")
    suspend fun deleteAllHistory()

    @Query("DELETE FROM analysis_history WHERE image_uri = :imageUri")
    suspend fun deleteHistoryByImageUri(imageUri: String)

}