package com.uiel.scul.network.api

import com.uiel.scul.model.culture.CultureDetailResponse
import com.uiel.scul.model.culture.FetchCultureTagResponse
import com.uiel.scul.model.culture.FetchCulturesResponse
import com.uiel.scul.model.culture.SearchCultureResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CultureApi {

    //문화 리스트 조회
    @GET("scul/cultures")
    suspend fun searchCultureList(
        @Header("Authorization") authorization: String,
    ): FetchCulturesResponse

    //문화 생활 상세보기
    @GET("scul/cultures/detail/{culture-id}")
    suspend fun cultureDetail(
        @Header("Authorization") authorization: String,
        @Path("culture-id") cultureId: String,
    ): CultureDetailResponse

    //문화 검색
    @GET("scul/cultures/search")
    suspend fun searchCulture(
        @Header("Authorization") authorization: String,
    ): SearchCultureResponse

    //문화 리스트 태그 조회
    @GET("scul/cultures/tag")
    suspend fun fetchCultureTag(
        @Header("Authorization") authorization: String,
    ): FetchCultureTagResponse
}