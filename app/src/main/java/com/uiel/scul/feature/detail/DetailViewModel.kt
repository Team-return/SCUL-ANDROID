package com.uiel.scul.feature.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.SculApplication
import com.uiel.scul.model.culture.CultureDetailResponse
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class DetailViewModel : ViewModel() {
    private val cultureApi = Retrofit.cultureApi
    private val accessToken = SculApplication.preferences.getString("AccessToken", "")

    private val _uiState = MutableStateFlow(getDefaultState())
    val uiState = _uiState.asStateFlow()

    fun getDetailCulture(
        cultureId: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            cultureApi.cultureDetail(
                authorization = accessToken,
                cultureId = cultureId,
            )
        }.onSuccess {response->
            _uiState.update {
                response.apply {
                    it.copy(
                        id = id,
                        location = location,
                        placeName = placeName,
                        ticketPrice = ticketPrice,
                        isBookMarked = isBookMarked,
                        imageUrl = imageUrl,
                        cultureName = cultureName,
                        wantedPeople = wantedPeople,
                        content = content,
                        phoneNumber = phoneNumber,
                        applicationStartDate = applicationStartDate,
                        applicationEndDate = applicationEndDate,
                        serviceStartDate = serviceStartDate,
                        serviceEndDate = serviceEndDate,
                        cultureLink = cultureLink,
                        xcoordinate = xcoordinate,
                        ycoordinate = ycoordinate,
                    )
                }
            }
        }.onFailure {
            Log.d("TEST2",it.toString())
        }
    }
}

fun getDefaultState(): CultureDetailResponse {
    return CultureDetailResponse(
        id = UUID.randomUUID(),
        location = "",
        placeName = "",
        ticketPrice = "",
        isBookMarked = false,
        imageUrl = "",
        cultureName = "",
        wantedPeople = "",
        content = "",
        phoneNumber = "",
        applicationStartDate = "",
        applicationEndDate = "",
        serviceStartDate = "",
        serviceEndDate = "",
        cultureLink = "",
        xcoordinate = 0f,
        ycoordinate = 0f
    )
}
