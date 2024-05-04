package com.uiel.scul.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.uiel.scul.R
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculTypography

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel(),
) {
    val event = viewModel.event
    var accountId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(event) {
        event.collect {
            when (it) {
                is LoginViewModel.Event.NavigateToMain -> {
                    navController.navigate("main")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 25.dp
                )
        ) {
            Text(
                text = "로그인하고\nSCUL을 사용해보세요",
                style = SculTypography.Heading3,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "아이디",
                style = SculTypography.Label2,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.gray_50),
                        shape = RoundedCornerShape(4.dp)
                    ),
                value = accountId,
                singleLine = true,
                onValueChange = { accountId = it },
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 12.dp,
                                top = 13.dp,
                                bottom = 13.dp,
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        if (accountId.isEmpty()) {
                            Text(text = "아이디")
                        }
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "비밀번호",
                style = SculTypography.Label2,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.gray_50),
                        shape = RoundedCornerShape(4.dp)
                    ),
                singleLine = true,
                value = password,
                onValueChange = { password = it },
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 12.dp,
                                top = 13.dp,
                                bottom = 13.dp,
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        if (password.isEmpty()) {
                            Text(text = "비밀번호")
                        }
                        innerTextField()
                    }
                }
            )
        }
        Spacer(modifier = Modifier.weight(2f))
        Column(
            modifier = Modifier
                .padding(
                    bottom = 40.dp,
                    start = 25.dp,
                    end = 25.dp,
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Text(
                    text = "아직 회원이 아니라면?",
                    style = SculTypography.Body2,
                    color = SculColor.BLACK,
                )
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Text(
                    modifier = Modifier.clickable(
                        onClick = {}
                    ),
                    text = "회원가입 하기",
                    style = SculTypography.Body2,
                    color = SculColor.MAIN500,
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SculColor.MAIN500,
                    contentColor = SculColor.WHITE,
                    disabledContainerColor = SculColor.GRAY300,
                    disabledContentColor = SculColor.WHITE,
                ),
                onClick = {
                    viewModel.login(
                        accountId = accountId,
                        password = password,
                    )
                },
                enabled = accountId.isNotEmpty() && password.isNotEmpty(),
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "로그인",
                    style = SculTypography.Button2,
                    color = SculColor.WHITE,
                )
            }
        }
    }
}
