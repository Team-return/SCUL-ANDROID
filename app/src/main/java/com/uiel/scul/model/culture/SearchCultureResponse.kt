package com.uiel.scul.model.culture

import java.util.UUID

data class SearchCulturesResponse(
    val culture: List<SearchCultureResponse>
)
data class SearchCultureResponse (
    val id: UUID,
    val location: String,
    val placeName: String,
    val ticketPrice: String,
    val isBookMarked: Boolean,
    val imageUrl: String,
    val cultureName: String,
    val wantedPeople: String,
    val phoneNumber: String,
    val applicationStartDate: String,
    val applicationEndDate: String,
)
