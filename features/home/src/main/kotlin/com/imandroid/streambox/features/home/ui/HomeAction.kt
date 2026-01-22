package com.imandroid.streambox.features.home.ui

import com.imandroid.streambox.core.architecture.Action
import com.imandroid.streambox.features.home.domain.HomeContent

sealed interface HomeAction : Action {
    data object Load : HomeAction
    data object Retry : HomeAction
    data class ContentLoaded(val items: List<HomeContent>) : HomeAction
    data class LoadingFailed(val message: String) : HomeAction
}
