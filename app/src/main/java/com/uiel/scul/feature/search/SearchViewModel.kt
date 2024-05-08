package com.uiel.scul.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.SculApplication
import com.uiel.scul.model.culture.SearchCultureResponse
import com.uiel.scul.model.culture.SearchCulturesResponse
import com.uiel.scul.model.myreview.FetchMyReviewsResponse
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val cultureApi = Retrofit.cultureApi
    private val bookmarkApi = Retrofit.bookmarkApi
    private val accessToken = SculApplication.preferences.getString("AccessToken", "")

    private val _uiState = MutableStateFlow(SearchCulturesResponse(listOf()))
    val uiState = _uiState.asStateFlow()
    fun searchCulture(
        keyword: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            cultureApi.searchCulture(
                authorization = accessToken,
                keyword = keyword,
            )
        }.onSuccess {response ->
            _uiState.update {
                it.copy(culture = response.culture)
            }
        }.onFailure {

        }
    }

    fun fetchCultureName(
        keyword: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            cultureApi.fetchCultureName(
                keyword = keyword,
            )
        }.onSuccess {

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
