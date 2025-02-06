package com.example.flutter.integration.data

import com.example.flutter.integration.data.model.convert
import com.example.flutter.integration.data.remote.AccountsApi
import com.example.flutter.integration.domain.AccountRepository
import com.example.flutter.integration.domain.RequestError
import com.example.flutter.integration.domain.Result
import com.example.flutter.integration.domain.model.AccountBalance
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountsApi: AccountsApi
) : AccountRepository {
    override suspend fun getBalance(address: String): Result<AccountBalance, RequestError> {
        return try {
            val response = accountsApi.getBalance(address = address)
            Result.Success(
                AccountBalance(
                    account = address,
                    balance = response.result.convert().toPlainString()
                )
            )
        } catch (e: Exception) {
            //Not doing anything with the error
            Result.Error(RequestError.Network.BAD_GATEWAY)
        }
    }

}