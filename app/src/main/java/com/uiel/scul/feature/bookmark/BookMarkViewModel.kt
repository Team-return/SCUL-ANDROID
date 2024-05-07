package com.uiel.scul.feature.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.SculApplication
import com.uiel.scul.feature.home.HomeUiState
import com.uiel.scul.model.bookmark.FetchBookmarkCulturesResponse
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookMarkViewModel : ViewModel() {
    private val bookmarkApi = Retrofit.bookmarkApi
    private val accessToken = SculApplication.preferences.getString("AccessToken", "")


    private val _uiState = MutableStateFlow(FetchBookmarkCulturesResponse(listOf()))
    val uiState = _uiState.asStateFlow()

    init {
        getBookMarkCultureList()
    }

    private fun getBookMarkCultureList() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            bookmarkApi.fetchBookMarkCulture(
                authorization = accessToken,
            )
        }.onSuccess { response ->
            _uiState.update { it.copy(culture = response.culture) }
        }.onFailure {

        }
    }

    fun bookMarkClick(
        cultureId: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            bookmarkApi.postBookMark(
                authorization = accessToken,
                cultureId = cultureId,
            )
        }.onSuccess {

        }.onFailure {

        }
    }
}