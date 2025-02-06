package com.example.flutter.integration.data.model.mapper

import com.example.flutter.integration.data.model.account.AccountBalanceDto
import com.example.flutter.integration.domain.model.AccountBalance

fun AccountBalanceDto.toDomain() = with(this){
    AccountBalance(
        balance = balance,
        account = account
    )
}