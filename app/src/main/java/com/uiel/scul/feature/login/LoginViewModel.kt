package com.uiel.scul.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.model.user.LoginRequest
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
    private val usersApi = Retrofit.usersApi

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun login(
        accountId: String,
        password: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            usersApi.login(
                LoginRequest(
                    accountId = accountId,
                    password = password,
                )
            )
        }.onSuccess {
            _event.emit(Event.NavigateToMain)
        }.onFailure {

        }
    }

    sealed interface Event {
        data object NavigateToMain : Event
    }
}
