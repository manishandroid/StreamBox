package com.imandroid.streambox.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imandroid.streambox.core.architecture.DispatcherProvider
import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCase
import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider,
    private val loadHomeContentUseCase: LoadHomeContentUseCase = LoadHomeContentUseCaseImpl()
) : ViewModel() {

    private val reducer = HomeReducer(dispatcherProvider)

    val state: StateFlow<HomeState> = reducer.state

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
            val result = loadHomeContentUseCase()
            val nextAction = result.fold(
                onSuccess = { HomeAction.ContentLoaded(it) },
                onFailure = { HomeAction.LoadingFailed(it.message ?: "Unable to load content") }
            )
            reducer.update(nextAction)
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
