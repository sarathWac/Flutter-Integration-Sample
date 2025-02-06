package com.example.flutter.integration.data.remote

import com.example.flutter.integration.data.model.BaseResponse
import com.example.flutter.integration.data.model.account.AccountBalanceDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountsApi {

    @GET("api")
    suspend fun getBalance(
        @Query("module") module: String = "account",
        @Query("action") action: String = "balance",
        @Query("address") address: String,
        @Query("tag") tag: String = "latest",
    ): BaseResponse<String>

    @GET("api")
    suspend fun getMultipleBalances(
        @Query("module") module: String = "account",
        @Query("action") action: String = "balancemulti",
        @Query("address") addresses: List<String>,
        @Query("tag") tag: String = "latest",
    ): BaseResponse<List<AccountBalanceDto>>

}