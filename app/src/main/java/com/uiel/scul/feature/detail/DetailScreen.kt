package com.uiel.scul.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    navController: NavController
) {
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
                text = "서울 시립 미술관",
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
            Content(name = "전화번호", data = "041-1234-568")
            Spacer(modifier = Modifier.height(16.dp))
            Content(name = "주소", data = "종로구, 서울 시립간")
            Spacer(modifier = Modifier.height(16.dp))
            Content(name = "이용시간", data = "08:00 ~ 14:00")
            Spacer(modifier = Modifier.height(16.dp))
            Content(name = "접수 일정", data = "5월 6일 ~ 4월3일")
            Spacer(modifier = Modifier.height(16.dp))
            Content(name = "운영 일정", data = "5월 6일 ~ 4월3일")
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(SculColor.GRAY50)
        )
        TabLayout()
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
private fun TabLayout() {
    val pages = listOf("상세 정보", "리뷰")
    var selectedItem by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            style = SculTypography.Body2,
                            color = if(pagerState.currentPage == index) SculColor.BLACK else SculColor.GRAY500,
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
        ) {page ->
            when(page) {
                0 -> DetailInfoScreen()
                1 -> DetailReviewScreen()
            }
        }
    }

}