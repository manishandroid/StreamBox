package com.imandroid.streambox.features.home.ui

import com.imandroid.streambox.core.architecture.State

sealed interface HomeState : State {
    data object Idle : HomeState
    data object Loading : HomeState
    data class Content(val items: List<FeaturedContent>) : HomeState
    data class Error(val message: String) : HomeState
}

data class FeaturedContent(
    val title: String,
    val year: String,
    val category: String
)
