package com.imandroid.streambox.features.home.domain

data class HomeContent(
    val title: String,
    val year: String,
    val category: String,
    val imageUrl: String? = null
)
