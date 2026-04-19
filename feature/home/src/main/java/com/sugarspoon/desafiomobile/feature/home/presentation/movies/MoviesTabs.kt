package com.sugarspoon.desafiomobile.feature.home.presentation.movies

import androidx.annotation.StringRes
import com.sugarspoon.desafiomobile.feature.home.R

enum class MoviesTabs(
    @param:StringRes val title: Int,
    val tag: String,
) {
    COMING_SOON(
        R.string.is_coming,
        tag = "Em breve"
    ),
    LATEST_PREMIERES(
        R.string.latest_premieres,
        tag = "Últimas estréias"
    );
}