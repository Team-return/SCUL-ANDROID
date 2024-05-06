package com.uiel.scul.model.bookmark

import java.util.UUID

data class FetchBookmarkCulturesResponse(
    val culture: List<FetchBookmarkCultureResponse>
)

data class FetchBookmarkCultureResponse(
    val id: UUID,
    val location: String,
    val placeName: String,
    val ticketPrice: String,
    val isBookMarked: Boolean,
    val imageUrl: String,
    val cultureName: String,
    val wantedPeople: String,
    val applicationStartDate: String,
    val applicationEndDate: String,
)
