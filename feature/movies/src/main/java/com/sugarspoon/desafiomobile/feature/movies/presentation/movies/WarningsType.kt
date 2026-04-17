package com.sugarspoon.desafiomobile.feature.movies.presentation.movies

import androidx.annotation.StringRes
import com.sugarspoon.desafiomobile.feature.movies.R

enum class WarningsType(
    @param:StringRes val title: Int,
    @param:StringRes val description: Int,
) {
    GENERIC_ERROR(
        title = R.string.warning_movies_generic_error_title,
        description = R.string.warning_movies_generic_error_description,
    ),
    NO_INTERNET_ERROR(
        title = R.string.warning_movies_no_internet_title,
        description = R.string.warning_movies_no_internet_description,
    ),
}