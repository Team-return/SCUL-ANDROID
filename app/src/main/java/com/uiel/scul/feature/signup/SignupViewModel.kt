package com.uiel.scul.feature.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.SculApplication
import com.uiel.scul.model.user.SignupRequest
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel(){
    private val usersApi = Retrofit.usersApi

    private val _event = MutableSharedFlow<SignupViewModel.Event>()
    val event = _event.asSharedFlow()

    fun signUp(
        name: String,
        accountId: String,
        password: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            usersApi.signup(
                SignupRequest(
                    name = name,
                    accountId = accountId,
                    password = password,
                )
            )
        }.onSuccess {
            SculApplication.preferences.setString("AccessToken","Bearer ${it.accessToken}")
            _event.emit(Event.NavigateToMain)
        }.onFailure {
            when(it.message) {
                "HTTP 409 " -> {
                    _event.emit(Event.Exist)
                    Log.d("TEST","in")
                }
            }
            Log.d("TEST",it.message.toString())
        }
    }

    sealed interface Event {
        data object NavigateToMain : Event
        data object Exist : Event
    }
}