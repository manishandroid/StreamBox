package com.imandroid.streambox.features.home.ui

import com.imandroid.streambox.core.architecture.DispatcherProvider
import com.imandroid.streambox.core.architecture.StateReducer

class HomeReducer(
    dispatcherProvider: DispatcherProvider
) : StateReducer<HomeState, HomeAction>(
    initialState = HomeState.Idle,
    dispatcherProvider = dispatcherProvider
) {
    override fun reduce(action: HomeAction, currentState: HomeState): HomeState =
        when (action) {
            HomeAction.Load -> HomeState.Loading
            HomeAction.Retry -> HomeState.Loading
            is HomeAction.ContentLoaded -> HomeState.Content(action.items)
            is HomeAction.LoadingFailed -> HomeState.Error(action.message)
        }
}
