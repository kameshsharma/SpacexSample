package com.example.samplespacex.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    val data = query().first()

    val resultFlow = if (shouldFetch(data)) {

        emit(Result.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Result.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Result.Error(throwable, it) }
        }
    } else {
        query().map { Result.Success(it) }
    }

    emitAll(resultFlow)
}