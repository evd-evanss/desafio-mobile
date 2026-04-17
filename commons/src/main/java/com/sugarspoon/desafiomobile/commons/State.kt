package com.sugarspoon.desafiomobile.commons

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface UiState

class State<State : UiState>(
    initialState: State,
) {
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun updateState(state: (State) -> State) {
        _uiState.update(state)
    }
}