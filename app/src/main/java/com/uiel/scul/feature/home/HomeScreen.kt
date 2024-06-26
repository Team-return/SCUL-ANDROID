package com.uiel.scul.feature.home

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.skydoves.landscapist.glide.GlideImage
import com.uiel.scul.R
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography
import com.uiel.scul.model.culture.FetchCultureResponse
import com.uiel.scul.network.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Image(
                modifier = Modifier.padding(
                    top = 5.dp,
                    bottom = 5.dp,
                    start = 20.dp,
                ),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        top = 12.dp,
                        bottom = 18.dp,
                        end = 20.dp,
                    ),
                painter = painterResource(id = R.drawable.ic_banner),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            // 필터 선택
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                if (uiState.culture.isEmpty()) {
                    items(10) {
                        LoadingShimmerEffect()
                    }
                } else {
                    items(uiState.culture.size) {
                        ItemCard(
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
}

@Composable
private fun Filter() {
    Row {

    }
}

@Composable
private fun ItemCard(
    moveToDetail: () -> Unit,
    uiState: FetchCultureResponse,
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
        horizontalArrangement = Arrangement.End,
    ) {
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

//        AsyncImage(
//            modifier = Modifier.size(80.dp),
//            model = ImageRequest.Builder(context)
//                .data("https://yeyak.seoul.go.kr/web/common/file/FileDown.do?file_id=1708992268823K9FSITBI6ZV5QXVOC3BWB93WH")
//                .addHeader("Accept", "image/*")
//                .crossfade(true)
//                .build(),
//            contentDescription = "",
//            onError = {
//                Log.d("TEST", it.result.throwable.message.toString())
//            },
//            error = painterResource(id = SculIcon.Logout),
//            imageLoader = imageLoader,
//        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
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
                    text = "예약 ",
                    style = SculTypography.Body3,
                    color = SculColor.GRAY900,
                )
                Text(
                    text = if (uiState.isApplicationAble) "가능" else "불가능",
                    style = SculTypography.Body3,
                    color = SculColor.MAIN700,
                )
                Spacer(modifier = Modifier.width(12.dp))
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
            Row(
                modifier = Modifier.width(250.dp)
            ) {
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
                            maxLines = 1,
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

@Composable
fun LoadingShimmerEffect() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerGridItem(brush = brush)
}

@Composable
private fun ShimmerGridItem(brush: Brush) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
        Spacer(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(brush)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Spacer(
                modifier = Modifier
                    .height(17.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Spacer(
                modifier = Modifier
                    .height(12.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Spacer(
                modifier = Modifier
                    .height(12.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .height(24.dp)
                    .width(50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(brush)
            )

        }
        Spacer(modifier = Modifier.weight(1f))
        Spacer(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}
