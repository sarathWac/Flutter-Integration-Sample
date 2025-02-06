package com.example.flutter.integration.data.model.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountBalanceDto(
    val account: String,
    val balance: String
)