package com.dicoding.asclepius.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "analysis_history")
class AnalysisHistoryEntity (

    @PrimaryKey(autoGenerate = true)
    var id :Int = 0,

    @ColumnInfo(name = "image_uri")
    var imageUri: String? = null,

    @ColumnInfo(name = "label")
    var label: String? = null,

    @ColumnInfo(name = "score")
    var score: String? = null,

)