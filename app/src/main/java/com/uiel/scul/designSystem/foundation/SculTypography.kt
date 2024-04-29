package com.uiel.scul.designSystem.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.uiel.scul.R

private val WantedSansFamily = FontFamily(
    listOf(
        Font(
            resId = R.font.wantedsans_semibold,
            weight = FontWeight.Bold,
        ),
        Font(
            resId = R.font.wantedsans_medium,
            weight = FontWeight.Medium,
        ),
    ),
)

private val platFormTextStyle = PlatformTextStyle(
    includeFontPadding = false,
)

object SculTypography {
    val Heading1
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 42.sp,
            fontWeight = FontWeight.SemiBold,
            platformStyle = platFormTextStyle,
        )

    val Heading2
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            platformStyle = platFormTextStyle,
        )

    val Heading3
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            platformStyle = platFormTextStyle,
        )

    val Heading4
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            platformStyle = platFormTextStyle,
        )

    val SB1
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val SB2
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val SB3
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val Body1
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val Body2
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val Body3
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val Caption1
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val Caption2
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val Label1
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val Label2
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            platformStyle = platFormTextStyle,
        )

    val Button1
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            platformStyle = platFormTextStyle,
        )

    val Button2
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            platformStyle = platFormTextStyle,
        )

    val Button3
        @Composable get() = TextStyle(
            fontFamily = WantedSansFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            platformStyle = platFormTextStyle,
        )
}
