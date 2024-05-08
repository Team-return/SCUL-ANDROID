package com.uiel.scul.feature.myPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.SculApplication
import com.uiel.scul.model.user.FetchNameResponse
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val usersApi = Retrofit.usersApi
    private val accessToken = SculApplication.preferences.getString("AccessToken", "")

    private val _uiState = MutableStateFlow(FetchNameResponse(""))
    val uiState = _uiState.asStateFlow()

    init {
        fetchName()
    }

    private fun fetchName() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            usersApi.fetchName(authorization = accessToken)
        }.onSuccess { response ->
            _uiState.update {
                it.copy(name = response.name)
            }
        }.onFailure {

        }
    }
}

