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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MyPageViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var isDialog by remember { mutableStateOf(false) }

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
                    text = "${uiState.name}님",
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
                        onClick = { navController.navigate("myReview") }
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
                        onClick = { navController.navigate("bookmark") }
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
                        onClick = { isDialog = !isDialog }
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
        if (isDialog) {
            LogOutDialog(
                onClickCancel = { isDialog = !isDialog },
                onClickConfirm = { navController.navigate("login")},
            )
        }
    }
}

@Composable
private fun LogOutDialog(
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit,
) {
    Dialog(
        onDismissRequest = onClickCancel
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = SculColor.WHITE,
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "SCUL에서 로그아웃 하시겠어요?",
                    style = SculTypography.SB1,
                    color = SculColor.BLACK,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "다시 로그인 할 때까지 사용할 수 없어요",
                    style = SculTypography.Body2,
                    color = SculColor.GRAY900,
                )
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Button(
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SculColor.GRAY200
                        ),
                        onClick = onClickCancel,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "취소",
                            style = SculTypography.Button2,
                            color = SculColor.WHITE,
                        )
                    }
                    Spacer(modifier = Modifier.width(22.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SculColor.MAIN600
                        ),
                        onClick = onClickConfirm,
                        shape = RoundedCornerShape(4.dp),
                    ) {
                        Text(
                            text = "확인",
                            style = SculTypography.Body2,
                            color = SculColor.WHITE,
                        )
                    }
                }
            }
        }
    }
}
