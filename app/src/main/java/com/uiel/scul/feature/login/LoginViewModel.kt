package com.uiel.scul.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.model.user.LoginRequest
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val usersApi = Retrofit.usersApi
    //private val usersApi = Retrofit.retrofit.create(UsersApi::)

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    private val _accountId = MutableLiveData("")
    val accountId: LiveData<String> = _accountId

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _buttonEnabled = MutableLiveData(false)
    val buttonEnabled: LiveData<Boolean> = _buttonEnabled

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

    private fun checkInput() {
        if(_accountId.value.isNotEmpty() && _password.value.isNotEmpty()) {
            _buttonEnabled.value
        }
    }

    sealed interface Event {
        data object NavigateToMain : Event
    }
}
