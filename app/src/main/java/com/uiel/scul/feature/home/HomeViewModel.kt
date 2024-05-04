package com.uiel.scul.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.SculApplication
import com.uiel.scul.feature.login.LoginViewModel
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val cultureApi = Retrofit.cultureApi
    private val accessToken = SculApplication.preferences.getString("AccessToken","")

    private val _event = MutableSharedFlow<LoginViewModel.Event>()
    val event = _event.asSharedFlow()

    private val _uiState = MutableStateFlow(HomeUiState(listOf()))
    val uiState = _uiState.asStateFlow()

    init {
        getCultureList()
    }
    private fun getCultureList() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            cultureApi.searchCultureList(
                authorization = accessToken
            )
        }.onSuccess {response->
            _uiState.update { it.copy(culture = response.culture) }
        }.onFailure {

        }
    }
}