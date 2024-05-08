package com.uiel.scul.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculTypography
import com.uiel.scul.model.review.FetchReviewResponse

@Composable
fun DetailReviewScreen(
    placeName: String,
    cultureId: String,
    navController: NavController,
    viewModel: DetailReviewViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchReivews(cultureId = cultureId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        if (uiState.reviewList.isEmpty()) {
            NoReview(
                navController = navController,
                cultureId = cultureId,
                placeName = placeName,
            )
        } else {
            uiState.reviewList.forEach {
                ReviewItem(uiState = it)
            }
        }
    }
}

@Composable
private fun ReviewItem(
    uiState: FetchReviewResponse
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
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
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
private fun NoReview(
    navController: NavController,
    cultureId: String,
    placeName: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 65.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "리뷰가 없습니다",
            style = SculTypography.Body1,
            color = SculColor.GRAY900,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 56.dp,
                    vertical = 12.dp,
                ),
            onClick = { navController.navigate("write/{$placeName}/{$cultureId}") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SculColor.MAIN400,
                contentColor = SculColor.WHITE,
            )
        ) {
            Text(
                text = "리뷰 작성하기",
                style = SculTypography.Body1,
                color = SculColor.WHITE,
            )
        }
    }
}