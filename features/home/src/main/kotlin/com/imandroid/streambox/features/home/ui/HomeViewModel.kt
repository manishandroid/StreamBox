package com.imandroid.streambox.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imandroid.streambox.core.architecture.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider
) : ViewModel() {

    private val reducer = HomeReducer(dispatcherProvider)

    val state: StateFlow<HomeState> = reducer.state

    private val placeholderItems = listOf(
        FeaturedContent(title = "Night Signal", year = "2024", category = "Sci-Fi"),
        FeaturedContent(title = "Harborline", year = "2023", category = "Drama"),
        FeaturedContent(title = "Glass District", year = "2022", category = "Thriller")
    )

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.Load,
            HomeAction.Retry -> loadContent()
            is HomeAction.ContentLoaded,
            is HomeAction.LoadingFailed -> dispatch(action)
        }
    }

    private fun loadContent() {
        viewModelScope.launch {
            reducer.update(HomeAction.Load)
            // Simulate async work so the loading state is visible without real data.
            delay(1200L)
            reducer.update(HomeAction.ContentLoaded(placeholderItems))
        }
    }

    private fun dispatch(action: HomeAction) {
        viewModelScope.launch {
            reducer.update(action)
        }
    }
}

private object DefaultDispatcherProvider : DispatcherProvider {
    override val main: CoroutineContext = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.IO
    override val default: CoroutineContext = Dispatchers.Default
}
