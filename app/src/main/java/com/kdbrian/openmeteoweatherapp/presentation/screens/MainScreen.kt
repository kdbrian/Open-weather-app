package com.kdbrian.openmeteoweatherapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kdbrian.openmeteoweatherapp.R
import com.kdbrian.openmeteoweatherapp.presentation.ui.OpenMeteoAppTheme
import com.kdbrian.openmeteoweatherapp.util.DateUtils
import com.kdbrian.openmeteoweatherapp.util.DateUtils.toFormattedDate

@Composable
fun MainScreen() {

    val today =
        System.currentTimeMillis().toFormattedDate(DateUtils.FORMAT_DAY_MONTH + " EEEE").split(" ")
    val context = LocalContext.current

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
                .padding(16.dp)
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .dropShadow(
                        shape = RoundedCornerShape(12.dp),
                        shadow = Shadow(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFFFFFFF),
                                    Color(0xFFD8C99B),
                                )
                            ),
                            radius = 12.dp,
                        )
                    ),
                shape = RoundedCornerShape(18.dp),
            ) {

                val textMeasurer = rememberTextMeasurer()
                val temp = textMeasurer.measure(
                    text = "22",
                    style = TextStyle(
                        fontSize = 64.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                val sunBitmap = ImageBitmap.imageResource(R.drawable.brightsun)
                val cloudBitmap = ImageBitmap.imageResource(R.drawable.calmclouds)

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(6.dp)
                        .drawBehind {

                            translate(
                                top = size.height.times(.2f),
                                left = size.width.times(.5f)
                            ){

//                                scale(scale = 1f){
                                    drawImage(
                                        image = cloudBitmap,
                                        srcSize = IntSize(100, 100)
                                    )
//                                }
                            }

                            translate(
                                top = size.height.times(.2f),
                                left = size.width.times(.6f)
                            ){

                                scale(scale = 1f){
                                    drawImage(
                                        image = sunBitmap,
                                    )
                                }
                            }

                            drawText(
                                textLayoutResult = temp,
                                topLeft = Offset(
                                    size.width.times(.4f),//.minus(temp.size.width),
                                    size.height.times(.3f)
                                )
                            )

                        }
                )
            }

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