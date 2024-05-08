package com.uiel.scul.feature.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculTypography
import com.uiel.scul.model.culture.CultureDetailResponse

@Composable
fun DetailInfoScreen(
    cultureId: String,
    uiState: CultureDetailResponse,
    navController: NavController,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            uiState.wantedPeople.split(",").apply {
                this.forEach {
                    Text(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = SculColor.MAIN500,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(
                                horizontal = 16.dp,
                                vertical = 10.dp,
                            ),
                        text = it,
                        style = SculTypography.Label2,
                        color = SculColor.BLACK,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(SculColor.GRAY50)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "이용료",
                style = SculTypography.Caption1,
                color = SculColor.BLACK,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .background(
                        color = SculColor.MAIN400,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(
                        vertical = 4.dp,
                        horizontal = 12.dp,
                    ),
                text = uiState.ticketPrice,
                style = SculTypography.Caption1,
                color = SculColor.WHITE,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Location(
            location = uiState.location,
            xCoordinate = uiState.xcoordinate,
            yCoordinate = uiState.ycoordinate,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(color = SculColor.GRAY50)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 32.dp,
                )
        ) {
            Text(
                text = "더 자세한 정보는 홈페이지 링크를 확인하세요!",
                style = SculTypography.Body2,
                color = SculColor.BLACK,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.clickable(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uiState.cultureLink))
                        context.startActivity(intent)
                    }
                ),
                text = uiState.cultureLink,
                style = SculTypography.Caption2,
                color = SculColor.MAIN600,
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(color = SculColor.GRAY50)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "혹시 이 곳을 다녀오셨나요?",
                style = SculTypography.Caption1,
                color = SculColor.BLACK
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 4.dp,
                    ),
                colors = ButtonDefaults.buttonColors(
                    contentColor = SculColor.WHITE,
                    containerColor = SculColor.MAIN400,
                    disabledContentColor = SculColor.WHITE,
                    disabledContainerColor = SculColor.MAIN300,
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = { navController.navigate("write/${uiState.placeName}/$cultureId") }
            ) {
                Text(
                    text = "리뷰 작성하기",
                    style = SculTypography.Body1,
                    color = SculColor.WHITE,
                )
            }
        }
    }
}

@Composable
private fun Location(
    location: String,
    xCoordinate: Double,
    yCoordinate: Double,
) {
    val latLng = LatLng(yCoordinate, xCoordinate)

    Row(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "위치보기",
            style = SculTypography.Caption1,
            color = SculColor.BLACK,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = location,
            style = SculTypography.Caption2,
            color = SculColor.GRAY900,
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
    if (latLng.latitude != 0.0 && latLng.longitude != 0.0) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(latLng, 14f)
        }

        GoogleMap(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp)),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = latLng),
                title = location,
            )
        }
    }

}