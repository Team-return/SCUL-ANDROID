package com.uiel.scul.model.culture

data class FetchCultureNamesResponse(
    val culture: List<FetchCultureName>
)

data class FetchCultureName(
    val cultureName: String,
)
