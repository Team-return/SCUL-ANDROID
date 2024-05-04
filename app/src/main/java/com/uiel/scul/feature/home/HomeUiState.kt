package com.uiel.scul.feature.home

import com.uiel.scul.model.culture.FetchCultureResponse

data class HomeUiState(
    val culture: List<FetchCultureResponse>
)
