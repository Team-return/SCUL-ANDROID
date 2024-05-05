package com.uiel.scul.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.SculApplication
import com.uiel.scul.model.review.FetchReviewsResponse
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailReviewViewModel : ViewModel() {
    private val reviewApi = Retrofit.reviewApi
    private val accessToken = SculApplication.preferences.getString("AccessToken", "")

    private val _uiState = MutableStateFlow(FetchReviewsResponse(listOf()))
    val uiState = _uiState.asStateFlow()

    fun fetchReivews(
        cultureId: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            reviewApi.fetchReview(
                authorization = accessToken,
                cultureId = cultureId,
            )
        }.onSuccess { response ->
            _uiState.update { it.copy(reviewList = response.reviewList) }
        }.onFailure {

        }
    }
}