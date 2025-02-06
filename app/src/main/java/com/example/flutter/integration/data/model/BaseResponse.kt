package com.example.flutter.integration.data.model

import kotlinx.serialization.Serializable


@Serializable
data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val result: T
)
