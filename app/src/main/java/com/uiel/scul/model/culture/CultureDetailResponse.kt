package com.uiel.scul.model.culture

import java.util.UUID

data class CultureDetailResponse(
    val id: UUID,
    val location: String,
    val placeName: String,
    val ticketPrice: String,
    val isBookMarked: Boolean,
    val imageUrl: String,
    val cultureName: String,
    val wantedPeople: String,
    val content: String,
    val phoneNumber: String,
    val isApplicationAble: Boolean,
    val applicationStartDate: String,
    val applicationEndDate: String,
    val serviceStartDate: String,
    val serviceEndDate: String,
    val cultureLink: String,
    val ycoordinate: Double,
    val xcoordinate: Double,
)
