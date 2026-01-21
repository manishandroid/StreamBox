package com.imandroid.streambox.features.home.ui

import com.imandroid.streambox.core.architecture.Action

sealed interface HomeAction : Action {
    data object Load : HomeAction
    data object Retry : HomeAction
    data class ContentLoaded(val items: List<FeaturedContent>) : HomeAction
    data class LoadingFailed(val message: String) : HomeAction
}
