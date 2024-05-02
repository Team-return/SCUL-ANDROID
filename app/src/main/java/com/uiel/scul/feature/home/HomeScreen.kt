package com.uiel.scul.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculTypography

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 12.dp, bottom = 18.dp),
//                painter = "",
//                contentDescription = "",
//            )
            // 필터 선택
            LazyColumn {

            }
        }
    }
}

@Composable
private fun ItemCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp,
            )
    ) {
//        Image(
//            modifier = Modifier.fillMaxHeight(),
//            painter = ,
//            contentDescription = "",
//        )
        Column {
            Text(
                text = "서울 시립 미술관",
                style = SculTypography.SB3,
                color = SculColor.BLACK
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = "서울특벌시 가정북76 우정관",
                style = SculTypography.Body3,
                color = SculColor.GRAY500,
            )
            Row {
                //예약 가능 이용료 무료
            }
        }
        IconButton(
            onClick = { /*TODO*/ }
        ) {

        }
    }
}