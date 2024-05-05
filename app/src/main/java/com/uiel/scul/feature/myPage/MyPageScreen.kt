package com.uiel.scul.feature.myPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
//            TopAppBar(
//                modifier = Modifier.fillMaxWidth(),
//                title = {
//                    Text(
//                        text = "MY",
//                        style = SculTypography.Caption1,
//                        color = SculColor.BLACK,
//                    )
//                },
//                windowInsets = WindowInsets(0.dp)
//            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "MY",
                    style = SculTypography.Caption1,
                    color = SculColor.BLACK,
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "즐거운 여가시간을 보내는",
                    style = SculTypography.Caption1,
                    color = SculColor.GRAY800,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "강해민님",
                    style = SculTypography.Heading3,
                    color = SculColor.BLACK,
                )
            }
            Spacer(modifier = Modifier.height(52.dp))
            Text(
                modifier = Modifier.padding(
                    vertical = 5.dp,
                    horizontal = 20.dp
                ),
                text = "보기",
                style = SculTypography.Body2,
                color = SculColor.GRAY500,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .clickable(
                        onClick = {}
                    )
                    .fillMaxWidth()
                    .padding(
                        vertical = 17.dp,
                        horizontal = 20.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = SculIcon.Review),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "내가 작성한 리뷰",
                    style = SculTypography.Body1,
                    color = SculColor.BLACK,
                )
            }
            Row(
                modifier = Modifier
                    .clickable(
                        onClick = {}
                    )
                    .fillMaxWidth()
                    .padding(
                        vertical = 17.dp,
                        horizontal = 20.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = SculIcon.BookMarkOn),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "북마크",
                    style = SculTypography.Body1,
                    color = SculColor.BLACK,
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                modifier = Modifier.padding(
                    vertical = 5.dp,
                    horizontal = 20.dp
                ),
                text = "계정",
                style = SculTypography.Body2,
                color = SculColor.GRAY500,
            )
            Row(
                modifier = Modifier
                    .clickable(
                        onClick = {}
                    )
                    .fillMaxWidth()
                    .padding(
                        vertical = 17.dp,
                        horizontal = 20.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = SculIcon.Logout),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "로그아웃",
                    style = SculTypography.Body1,
                    color = SculColor.BLACK,
                )
            }
        }
    }

}