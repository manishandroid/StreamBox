package com.imandroid.streambox.features.home.ui

import com.imandroid.streambox.core.architecture.State
import com.imandroid.streambox.features.home.domain.HomeContent

sealed interface HomeState : State {
    data object Idle : HomeState
    data object Loading : HomeState
    data class Content(val items: List<HomeContent>) : HomeState
    data class Error(val message: String) : HomeState
}
