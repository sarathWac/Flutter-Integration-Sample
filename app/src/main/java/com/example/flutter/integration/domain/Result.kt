package com.example.flutter.integration.domain

sealed interface Result<out T, out R : AppError> {
    data class Success<T>(val data: T) : Result<T, Nothing>
    data class Error<R : AppError>(val error: R) : Result<Nothing, R>
    data object Loading : Result<Nothing, Nothing>
}




sealed interface AppError

sealed interface RequestError : AppError {
    data class DataError<T>(val errorData: T) : RequestError
    data class GenericError(val message: String) : RequestError
    enum class Network : RequestError {
        NO_INTERNET,
        BAD_GATEWAY,
        TIMEOUT,
        SERVICE_UNAVAILABLE,
        SERIALIZATION
    }
}