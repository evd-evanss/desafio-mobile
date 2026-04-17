package com.sugarspoon.desafiomobile.commons

sealed class Response<out TResultModel> {
    companion object {
        fun <TResultModel> getResult(resultModel: TResultModel) =
            when (resultModel) {
                null -> Failure(Throwable("result model is null"))
                else -> Success(resultModel)
            }
    }
    
    data class Success<out TResultModel>(val resultModel: TResultModel) : Response<TResultModel>()
    data class Failure(val throwable: Throwable) : Response<Nothing>()
}

inline fun <TResultModel, MappedResult> Response<TResultModel>.onSuccess(
    callback: (TResultModel) -> (MappedResult),
): Response<MappedResult> =
    when (this) {
        is Response.Success ->
            try {
                Response.Success(callback.invoke(this.resultModel))
            } catch (e: Exception) {
                Response.Failure(e)
            }

        is Response.Failure -> this
    }

inline fun <TResultModel> Response<TResultModel>.onFailure(
    callback: (Throwable) -> Unit,
): Response<TResultModel> {
    if (this is Response.Failure) callback(this.throwable ?: UnknownException())
    return this
}

class UnknownException : Exception()