package com.uiel.scul.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uiel.scul.R
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    var inputText by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(
                    color = colorResource(id = R.color.gray_50),
                    shape = RoundedCornerShape(8.dp)
                ),
            singleLine = true,
            textStyle = SculTypography.Label2,
            value = inputText,
            onValueChange = { inputText = it },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 12.dp,
                            vertical = 12.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(id = SculIcon.Search),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    if (inputText.isEmpty()) {
                        Text(
                            text = "문화 생활 검색...",
                            style = SculTypography.Label2,
                            color = SculColor.GRAY400,
                        )
                    }
                    innerTextField()
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        modifier = Modifier.size(18.dp),
                        onClick = { inputText = "" }
                    ) {
                        Icon(
                            painter = painterResource(id = SculIcon.Cancel),
                            contentDescription = "",
                        )
                    }
                }
            }
        )
    }
}