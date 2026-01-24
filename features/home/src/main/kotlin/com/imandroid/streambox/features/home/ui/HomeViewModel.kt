package com.imandroid.streambox.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imandroid.streambox.core.architecture.DispatcherProvider
import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.data.HomeContentRepositoryImpl
import com.imandroid.streambox.features.home.data.mapper.HomeContentDtoListMapper
import com.imandroid.streambox.features.home.data.mapper.HomeContentDtoMapper
import com.imandroid.streambox.features.home.data.network.HomeNetworkModule
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.repository.HomeContentRepository
import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCase
import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCaseImpl
import com.imandroid.streambox.features.home.mapper.HomeContentUiListMapper
import com.imandroid.streambox.features.home.mapper.HomeContentUiMapper
import com.imandroid.streambox.features.home.ui.model.HomeContentUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider,
    private val homeContentRepository: HomeContentRepository = HomeContentRepositoryImpl(
        api = HomeNetworkModule.provideHomeContentApi(),
        mapper = HomeContentDtoListMapper(HomeContentDtoMapper()),
        dispatcherProvider = dispatcherProvider
    ),
    private val loadHomeContentUseCase: LoadHomeContentUseCase =
        LoadHomeContentUseCaseImpl(homeContentRepository),
    private val homeContentUiMapper: Mapper<List<HomeContent>, List<HomeContentUi>> =
        HomeContentUiListMapper(HomeContentUiMapper())
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

private object DefaultDispatcherProvider : DispatcherProvider {
    override val main: CoroutineContext = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.IO
    override val default: CoroutineContext = Dispatchers.Default
}
