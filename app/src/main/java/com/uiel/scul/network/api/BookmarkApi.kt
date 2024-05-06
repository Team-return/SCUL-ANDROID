package com.uiel.scul.network.api

import com.uiel.scul.model.bookmark.FetchBookmarkCulturesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BookmarkApi {

    @POST("scul/bookMarks/{culture-id}")
    suspend fun postBookMark(
        @Header("Authorization") authorization: String,
        @Path("culture-id") cultureId: String,
    )

    @GET("scul/bookMarks")
    suspend fun fetchBookMarkCulture(
        @Header("Authorization") authorization: String,
    ): FetchBookmarkCulturesResponse
}