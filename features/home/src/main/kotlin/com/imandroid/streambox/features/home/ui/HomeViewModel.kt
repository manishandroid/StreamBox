package com.imandroid.streambox.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imandroid.streambox.core.architecture.DispatcherProvider
import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCase
import com.imandroid.streambox.features.home.ui.model.HomeContentUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val loadHomeContentUseCase: LoadHomeContentUseCase,
    private val homeContentUiMapper: Mapper<List<HomeContent>, List<HomeContentUi>>
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
                onSuccess = { HomeAction.ContentLoaded(homeContentUiMapper.map(it)) },
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
