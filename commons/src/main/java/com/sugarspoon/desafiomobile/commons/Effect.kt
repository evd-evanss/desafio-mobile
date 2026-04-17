package com.sugarspoon.desafiomobile.commons

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface UiEffect

class Effect<Effect : UiEffect> {
    private val _uiEffect = MutableSharedFlow<Effect>()

    val uiEffect = _uiEffect.asSharedFlow()

    suspend fun emit(effect: Effect) {
        _uiEffect.emit(effect)
    }
}