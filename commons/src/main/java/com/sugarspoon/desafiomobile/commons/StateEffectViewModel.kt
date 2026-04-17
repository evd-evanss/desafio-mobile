package com.sugarspoon.desafiomobile.commons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class StateEffectViewModel<State: UiState, Effect: UiEffect>(
    initialState: State,
) : ViewModel() {
    private val viewModelState = State(initialState)
    private val viewModelEffect = Effect<Effect>()

    val state = viewModelState.uiState
    val effect = viewModelEffect.uiEffect

    protected fun setState(
        state: (State) -> State
    ) {
        viewModelState.updateState(state)
    }

    protected open fun sendEffect(
        effect: Effect
    ) {
        viewModelScope.launch {
            viewModelEffect.emit(effect)
        }
    }
}