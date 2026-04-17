package com.sugarspoon.desafiomobile.commons

import androidx.lifecycle.ViewModel

abstract class StateViewModel<State: UiState>(
    initialState: State,
) : ViewModel() {
    private val viewModelState = State(initialState)
    val state = viewModelState.uiState

    protected fun setState(
        state: (State) -> State
    ) {
        viewModelState.updateState(state)
    }
}