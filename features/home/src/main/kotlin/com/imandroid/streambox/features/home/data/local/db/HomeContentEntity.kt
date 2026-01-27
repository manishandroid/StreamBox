package com.imandroid.streambox.features.home.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_content")
data class HomeContentEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val year: String,
    val category: String,
    val imageUrl: String?
)
