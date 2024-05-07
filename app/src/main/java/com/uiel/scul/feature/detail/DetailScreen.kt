package com.uiel.scul.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography
import com.uiel.scul.model.culture.CultureDetailResponse
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun DetailScreen(
    cultureId: String,
    navController: NavController,
    viewModel: DetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getDetailCulture(cultureId = cultureId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = SculIcon.Search),
            contentDescription = "",
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 12.dp,
                    horizontal = 20.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = uiState.placeName,
                style = SculTypography.Heading3,
                color = SculColor.BLACK,
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = SculIcon.BookMarkOff),
                    contentDescription = "",
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(SculColor.GRAY50)
        )
        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 16.dp,
            )
        ) {
            Content(name = "전화번호", data = uiState.phoneNumber)
            Spacer(modifier = Modifier.height(16.dp))
            Content(name = "주소", data = uiState.location)
            Spacer(modifier = Modifier.height(16.dp))
            Content(name = "이용시간", data = "${uiState.serviceStartDate} ~ ${uiState.serviceEndDate}")
            Spacer(modifier = Modifier.height(16.dp))
            Content(
                name = "접수 일정",
                data = "${uiState.applicationStartDate} ~ ${uiState.applicationEndDate}"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Content(name = "운영 일정", data = "5월 6일 ~ 4월3일")
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(SculColor.GRAY50)
        )
        TabLayout(
            uiState = uiState,
            cultureId = cultureId,
            navController = navController,
        )
    }
}

@Composable
private fun Content(
    name: String,
    data: String,
) {
    Row {
        Text(
            text = name,
            style = SculTypography.Caption2,
            color = SculColor.GRAY400,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = data,
            style = SculTypography.Caption2,
            color = SculColor.BLACK,
        )
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
private fun TabLayout(
    uiState: CultureDetailResponse,
    cultureId: String,
    navController: NavController,
) {
    val pages = listOf("상세 정보", "리뷰")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = SculColor.MAIN400,
                )
            }
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            style = SculTypography.Body2,
                            color = if (pagerState.currentPage == index) SculColor.BLACK else SculColor.GRAY500,
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> DetailInfoScreen(
                    uiState = uiState,
                    navController = navController,
                )
                1 -> DetailReviewScreen(
                    cultureId = cultureId,
                    navController = navController,
                )
            }
        }
    }

}