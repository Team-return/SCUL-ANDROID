package com.uiel.scul.feature.write

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.nio.file.Files

private const val MAX_SCREENSHOT_COUNT = 4

class WriteReviewViewModel : ViewModel(){
    private val cultureApi = Retrofit.cultureApi

    private val screenshots: SnapshotStateList<Uri> = mutableStateListOf()

    private val _event = MutableSharedFlow<WriteReviewViewModel.Event>()
    val event = _event.asSharedFlow()

    private val _uiState = MutableStateFlow(screenshots)
    val uiState = _uiState.asStateFlow()
    fun addUris(uris: List<Uri>) {
        if (screenshots.size + uris.size > MAX_SCREENSHOT_COUNT) {
            //_event.emit(Event.MaxScreenshotCount)
        } else {
            screenshots.addAll(uris)
            //createImg()
        }
    }

    fun getUris(): List<Uri> = screenshots
    private fun createImg(
        files: List<File>,
        file: File
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            cultureApi.createImage(
                file = Files.readAllBytes(file.toPath()).toRequestBody(
                    contentType = "multipart/form-data".toMediaTypeOrNull()
                )
            )
        }

    }

    sealed interface Event {
        data object MaxScreenshotCount : Event
        data object Exist : Event
    }
}