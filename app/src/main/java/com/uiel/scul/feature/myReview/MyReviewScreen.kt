package com.uiel.scul.feature.myReview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uiel.scul.model.myreview.FetchMyReviewResponse

@Composable
fun MyReviewScreen(
    navController: NavController,
    viewModel: MyReviewViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
                modifier = Modifier.padding(20.dp),
                text = "내가 작성한 리뷰",
                style = SculTypography.SB1,
                color = SculColor.BLACK,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = SculColor.GRAY100)
            )
            if(uiState.reviewList.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "작성한 리뷰가 없습니다.",
                        style = SculTypography.SB1,
                        color = SculColor.GRAY500,
                    )
                }
            }
            LazyColumn {
                items(uiState.reviewList.size) {
                    MyWriteReviewItem(uiState = uiState.reviewList[it])
                }
            }
        }
    }
}

@Composable
private fun MyWriteReviewItem(
    uiState: FetchMyReviewResponse
) {
    Spacer(modifier = Modifier.height(28.dp))
    Text(
        modifier = Modifier.padding(start = 20.dp),
        text = "서울 시립 미술관에서",
        style = SculTypography.Caption1,
        color = SculColor.BLACK,
    )
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = uiState.writer,
            style = SculTypography.Body1,
            color = SculColor.GRAY700,
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = uiState.createdAt,
            style = SculTypography.Body1,
            color = SculColor.GRAY700,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = uiState.content,
            style = SculTypography.Body2,
            color = SculColor.BLACK,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(color = SculColor.GRAY50),
        )
    }
}