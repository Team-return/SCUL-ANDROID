package com.uiel.scul.feature.write

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uiel.scul.FileUtil
import com.uiel.scul.SculApplication
import com.uiel.scul.model.review.WriteReviewRequest
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.create
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Url
import java.io.File
import java.nio.file.Files

private const val MAX_SCREENSHOT_COUNT = 4

class WriteReviewViewModel : ViewModel() {
    private val cultureApi = Retrofit.cultureApi
    private val reviewApi = Retrofit.reviewApi
    private val accessToken = SculApplication.preferences.getString("AccessToken", "")


    private val screenshots: SnapshotStateList<Uri> = mutableStateListOf()
    private val files: SnapshotStateList<File> = mutableStateListOf()
    private val urls: SnapshotStateList<String> = mutableStateListOf()


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
    ) = viewModelScope.launch(Dispatchers.IO) {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)

        files.forEachIndexed { index, file ->
            requestBody.addFormDataPart(
                name = "file$index",
                filename = file.name,
                body = create("multipart/form-data".toMediaTypeOrNull(), file)
            )
        }
        runCatching {
            cultureApi.createImage(
                file = requestBody.build()
            )
        }.onSuccess {
            it.imageUrls.forEach { uri ->
                urls.add(uri)
            }
        }
    }

    fun writeReview(
        context: Context,
        cultureId: String,
        content: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        screenshots.forEach {
            files.add(
                FileUtil.toFile(
                    context = context,
                    uri = it,
                )
            )
        }
        createImg(
            files = files
        )
        runCatching {
            reviewApi.writeReview(
                authorization = accessToken,
                cultureId = cultureId,
                writeReviewRequest = WriteReviewRequest(
                    content = content,
                    imageUrls = urls
                )
            )
        }.onSuccess {

        }
    }

    sealed interface Event {
        data object MaxScreenshotCount : Event
        data object Exist : Event
    }
}
