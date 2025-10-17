package com.kdbrian.openmeteoweatherapp.presentation.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dagger.Lazy
import com.kdbrian.openmeteoweatherapp.R


val alanSans by lazy {
    FontFamily(
        Font(R.font.alansans_black,),
        Font(R.font.alansans_regular),
        Font(R.font.alansans_bold),
        Font(R.font.alansans_light),
        Font(R.font.alansans_medium),
        Font(R.font.alansans_extrabold),
        Font(R.font.alansans_semibold),
    )
}

val nosifer by lazy {
    FontFamily(
        Font(R.font.nosifer_regular),
    )
}

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
)