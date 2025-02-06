package com.example.flutter.integration.domain

import com.example.flutter.integration.domain.model.AccountBalance

interface AccountRepository {
    suspend fun getBalance(address: String): Result<AccountBalance, RequestError>
}