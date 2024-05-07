package com.uiel.scul.network.api

import com.uiel.scul.model.myreview.FetchMyReviewsResponse
import com.uiel.scul.model.review.FetchReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ReviewApi {

    //리뷰 작성
    suspend fun writeReview(
        @Header("Authorization") authorization: String,
    )

    //리뷰 조회
    @GET("scul/reviews/{culture-id}")
    suspend fun fetchReview(
        @Header("Authorization") authorization: String,
        @Path("culture-id") cultureId: String,
    ): FetchReviewsResponse

    //내가 작성한 리뷰 조회
    @GET("scul/reviews")
    suspend fun fetchMyReview(
        @Header("Authorization") authorization: String,
    ): FetchMyReviewsResponse
}
