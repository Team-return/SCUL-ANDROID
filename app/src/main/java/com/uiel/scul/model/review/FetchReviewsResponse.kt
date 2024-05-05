package com.uiel.scul.model.review

import java.util.UUID

data class FetchReviewsResponse(
    val reviews: List<FetchReviewResponse>
)

data class FetchReviewResponse(
    val id: UUID,
    val writer: String,
    val content: String,
    val createdAt: String,
)

