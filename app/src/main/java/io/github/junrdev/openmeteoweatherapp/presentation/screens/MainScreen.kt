package io.github.junrdev.openmeteoweatherapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.junrdev.openmeteoweatherapp.LocalFontFamily
import io.github.junrdev.openmeteoweatherapp.presentation.ui.OpenMeteoAppTheme
import io.github.junrdev.openmeteoweatherapp.util.DateUtils
import io.github.junrdev.openmeteoweatherapp.util.DateUtils.toFormattedDate

@Composable
fun MainScreen() {

    val today =
        System.currentTimeMillis().toFormattedDate(DateUtils.FORMAT_DAY_MONTH + " EEEE").split(" ")

    /*val all = listOf("Temperature", "Cloud cover", "Wind speed")
    val currentIndex = remember { mutableIntStateOf(0) }
    val delay = 1_400L
    LaunchedEffect(Unit) {
        while (true) {
            delay(delay)
            if (currentIndex.intValue == all.size - 1)
                currentIndex.intValue = 0
            else
                currentIndex.intValue = currentIndex.intValue.inc()
        }
    }*/

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFD1DBE1),
                            Color(0xFFBBC3C9),
                        )
                    )
                )
                .padding(12.dp)
        ) {

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = 64.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    ) {
                        withStyle(
                            SpanStyle(
                            )
                        ) {
                            append(today[0])
                        }
                        append(' ')
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Light
                            )
                        ) {

                            append(today[1])
                        }
                    }

                    append('\n')
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 24.sp
                        )
                    ) {
                        append(today[2])
                    }
                },
                fontFamily = LocalFontFamily.current,
            )

            Text(
                text ="",
                fontFamily = LocalFontFamily.current,
            )


        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPrev() {
    OpenMeteoAppTheme {
        MainScreen()
    }
}