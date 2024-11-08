package com.dicoding.asclepius.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.asclepius.data.local.entity.AnalysisHistoryEntity

@Database(entities = [AnalysisHistoryEntity::class], version = 1, exportSchema = false)
abstract class AnalysisHistoryDatabase : RoomDatabase() {
    abstract fun analysisHistoryDao(): AnalysisHistoryDao

    companion object {
        @Volatile
        private var instance: AnalysisHistoryDatabase? = null

        fun getInstance(context: Context): AnalysisHistoryDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AnalysisHistoryDatabase::class.java,
                    "AnalysisHistory.db"
                ).build()
            }
    }

}
