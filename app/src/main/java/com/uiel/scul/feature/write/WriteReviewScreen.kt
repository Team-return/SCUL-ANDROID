package com.uiel.scul.feature.write

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.uiel.scul.R
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography

@Composable
fun WriteReviewScreen(
    placeName: String,
    cultureId: String,
    navController: NavController,
    viewModel: WriteReviewViewModel = viewModel(),
) {
    val context = LocalContext.current
    var content by remember { mutableStateOf("") }

    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = viewModel::addUris,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 7.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { navController.navigateUp() }
            ) {
                Icon(
                    painter = painterResource(id = SculIcon.Back),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .clickable(
                        enabled = content.isNotEmpty(),
                        onClick = {
                            viewModel.writeReview(
                                context = context,
                                cultureId = cultureId,
                                content = content,
                            )
                        },
                    ),
                text = "리뷰 등록",
                style = SculTypography.Label1,
                color = if (content.isEmpty()) SculColor.GRAY400 else SculColor.MAIN500,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "리뷰 작성",
            style = SculTypography.SB1,
            color = SculColor.BLACK,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "방문한 곳",
                style = SculTypography.Caption1,
                color = SculColor.GRAY400,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = placeName,
                style = SculTypography.Caption1,
                color = SculColor.BLACK,
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ) {
            Text(
                text = "내용",
                style = SculTypography.Label2,
                color = SculColor.BLACK,
            )
            Text(
                text = "*",
                style = SculTypography.Label2,
                color = SculColor.MAIN500,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(horizontal = 20.dp)
                .background(
                    color = colorResource(id = R.color.gray_50),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = content,
            onValueChange = { content = it },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                            vertical = 10.dp,
                        ),
                    //verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    if (content.isEmpty()) {
                        Text(
                            text = "리뷰의 내용을 작성해주세요",
                            style = SculTypography.Caption2,
                            color = SculColor.GRAY400,
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "사진",
            style = SculTypography.Label2,
            color = SculColor.BLACK,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            IconButton(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = SculColor.GRAY50,
                        shape = RoundedCornerShape(8.dp),
                    ),
                onClick = { activityResultLauncher.launch(PickVisualMediaRequest()) }
            ) {
                Icon(
                    painter = painterResource(id = SculIcon.Plus),
                    contentDescription = "",
                )
            }
            viewModel.getUris().forEach {
                Spacer(modifier = Modifier.width(12.dp))
                AsyncImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    model = it,
                    contentDescription = "",
                )
            }
        }
    }
}
