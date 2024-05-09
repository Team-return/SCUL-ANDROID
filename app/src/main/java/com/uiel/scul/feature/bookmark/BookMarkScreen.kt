package com.uiel.scul.feature.bookmark

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography
import com.uiel.scul.model.culture.FetchCultureResponse
import okhttp3.OkHttpClient
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uiel.scul.model.bookmark.FetchBookmarkCultureResponse

@Composable
fun BookMarkScreen(
    navController: NavController,
    viewModel: BookMarkViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    painter = painterResource(id = SculIcon.Back),
                    contentDescription = "",
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "북마크",
                style = SculTypography.SB1,
                color = SculColor.BLACK,
            )
            Spacer(modifier = Modifier.height(24.dp))
            if(uiState.culture.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "북마크가 없습니다.",
                        style = SculTypography.SB1,
                        color = SculColor.GRAY500,
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                items(uiState.culture.size) {
                    BookMarkItem(
                        moveToDetail = { navController.navigate("detail/${uiState.culture[it].id}") },
                        uiState = uiState.culture[it],
                        context = context,
                        onBookMarkClick = { viewModel.bookMarkClick(uiState.culture[it].id.toString()) },
                    )
                }
            }
        }
    }
}

@Composable
private fun BookMarkItem(
    moveToDetail: () -> Unit,
    uiState: FetchBookmarkCultureResponse,
    context: Context,
    onBookMarkClick: () -> Unit,
) {
    var isBookMarked by remember { mutableStateOf(uiState.isBookMarked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = moveToDetail
            )
            .border(
                width = 0.5.dp,
                shape = RoundedCornerShape(8.dp),
                color = SculColor.GRAY100,
            )
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
//        Image(
//            modifier = Modifier
//                .fillMaxHeight(),
//            painter = painterResource(id = R.drawable.ic_logo),
//            contentDescription = "",
//        )
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val originalRequest = it.request()

                val newRequest = originalRequest.newBuilder()
                    .addHeader("Host", "yeyak.seoul.go.kr") // 원하는 Host 헤더로 교체합니다.
                    .build()

                it.proceed(newRequest)
            }
            .build()

        val imageLoader = ImageLoader.Builder(context)
            .okHttpClient(okHttpClient)
            .build()
        AsyncImage(
            modifier = Modifier.fillMaxHeight(),
            model = ImageRequest.Builder(context)
                .data("https://yeyak.seoul.go.kr/web/common/file/FileDown.do?file_id=1708992268823K9FSITBI6ZV5QXVOC3BWB93WH")
                //.addHeader("Accept","")
                //.addHeader("Host","yeyak.seoul.go.kr")
                //.addHeader("User-Agent","PostmanRuntime/7.38.0")
                .crossfade(true)
                .build(),
            contentDescription = "",
            onError = { Log.d("TEST", it.toString()) },
            error = painterResource(id = SculIcon.Logout),
            imageLoader = imageLoader,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
        ) {
            Text(
                text = uiState.placeName,
                style = SculTypography.SB3,
                color = SculColor.BLACK
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = uiState.location,
                style = SculTypography.Body3,
                color = SculColor.GRAY500,
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Row {
                Text(
                    text = "이용료 ",
                    style = SculTypography.Body3,
                    color = SculColor.GRAY900,
                )
                Text(
                    text = uiState.ticketPrice,
                    style = SculTypography.Body3,
                    color = SculColor.MAIN700
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                uiState.wantedPeople.split(",").apply {
                    this.forEach {
                        Text(
                            modifier = Modifier
                                .background(
                                    color = SculColor.MAIN500,
                                    shape = RoundedCornerShape(20.dp),
                                )
                                .padding(
                                    vertical = 6.dp,
                                    horizontal = 10.dp
                                ),
                            text = it,
                            style = SculTypography.Body3,
                            color = SculColor.WHITE,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                onBookMarkClick()
                isBookMarked = !isBookMarked
            }
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = if (isBookMarked) SculIcon.BookMarkOn else SculIcon.BookMarkOff),
                contentDescription = "",
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}