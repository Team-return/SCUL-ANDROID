package com.uiel.scul

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uiel.scul.designSystem.foundation.SculColor
import com.uiel.scul.designSystem.foundation.SculIcon
import com.uiel.scul.designSystem.foundation.SculTypography
import com.uiel.scul.feature.home.HomeScreen
import com.uiel.scul.feature.myPage.MyPageScreen
import com.uiel.scul.feature.search.SearchScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = SculColor.WHITE,
            ) {
                BottomNavigationItem(
                    selectedContentColor = SculColor.MAIN500,
                    unselectedContentColor = SculColor.GRAY900,
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = {
                        Column {
                            Spacer(modifier = Modifier.height(12.dp))
                            Icon(
                                painter = painterResource(id = SculIcon.Home),
                                contentDescription = "",
                                tint = if (selectedItem == 0) SculColor.MAIN500 else SculColor.GRAY900
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                    },
                    label = {
                        Text(
                            text = "홈",
                            style = SculTypography.Caption2,
                            color = if (selectedItem == 0) SculColor.MAIN500 else SculColor.GRAY900,
                        )
                    }
                )
                BottomNavigationItem(
                    selectedContentColor = SculColor.MAIN500,
                    unselectedContentColor = SculColor.GRAY900,
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = {
                        Column {
                            Spacer(modifier = Modifier.height(12.dp))
                            Icon(
                                painter = painterResource(id = SculIcon.Search),
                                contentDescription = "",
                                tint = if (selectedItem == 1) SculColor.MAIN500 else SculColor.GRAY900
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    },
                    label = {
                        Text(
                            text = "검색",
                            style = SculTypography.Caption2,
                            color = if (selectedItem == 1) SculColor.MAIN500 else SculColor.GRAY900,
                        )
                    }
                )
                BottomNavigationItem(
                    selectedContentColor = SculColor.MAIN500,
                    unselectedContentColor = SculColor.GRAY900,
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = {
                        Column {
                            Spacer(modifier = Modifier.height(12.dp))
                            Icon(
                                painter = painterResource(id = SculIcon.MyPage),
                                contentDescription = "",
                                tint = if (selectedItem == 2) SculColor.MAIN500 else SculColor.GRAY900
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    },
                    label = {
                        Text(
                            text = "MY",
                            style = SculTypography.Caption2,
                            color = if (selectedItem == 2) SculColor.MAIN500 else SculColor.GRAY900,
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        when (selectedItem) {
            0 -> HomeScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )

            1 -> SearchScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
            )

            2 -> MyPageScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
            )
        }
    }
}
