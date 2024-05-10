package com.uiel.scul.model.review

data class WriteReviewRequest(
    val content: String,
    val imageUrls: List<String>,
    val placeName: String,
)

