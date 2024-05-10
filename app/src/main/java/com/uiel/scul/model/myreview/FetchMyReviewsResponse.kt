package com.uiel.scul.model.myreview

import java.util.UUID

data class FetchMyReviewsResponse(
    val reviewList: List<FetchMyReviewResponse>

)

data class FetchMyReviewResponse(
    val id: UUID,
    val writer: String,
    val content: String,
    val createdAt: String,
    val imageUrls: List<String>,
    val placeName: String,
)
