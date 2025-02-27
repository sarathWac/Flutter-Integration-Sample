package com.example.flutter.integration.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flutter.integration.domain.AccountRepository
import com.example.flutter.integration.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val state: StateFlow<MainState> = _state.asStateFlow()

    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    fun updateAccountAddress(address: String) {
        _uiState.value = _uiState.value.copy(accountAddress = address)
    }

    fun getBalance() {
        _state.value = MainState.Loading
        viewModelScope.launch {
            val response = accountRepository.getBalance(_uiState.value.accountAddress)

            if (response is Result.Success) {
                _state.value = MainState.Fetched(response.data.balance)
            } else if(response is Result.Error) _state.value = MainState.Error("${response.error}")
        }
    }
}

data class UIState(
    val accountAddress: String = "",
    val balance: String = ""
)

sealed class MainState {
    data object Loading : MainState()
    data class Fetched(val balance: String) : MainState()
    data class Error(val message: String) : MainState()
}