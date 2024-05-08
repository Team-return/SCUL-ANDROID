package com.uiel.scul.model.culture

import java.util.UUID

data class FetchCulturesResponse(
    val culture: List<FetchCultureResponse>
)

data class FetchCultureResponse(
    val id: UUID,
    val location: String,
    val placeName: String,
    val ticketPrice: String,
    val isBookMarked: Boolean,
    val imageUrl: String,
    val cultureName: String,
    val wantedPeople: String,
    val isApplicationAble: Boolean,
)
