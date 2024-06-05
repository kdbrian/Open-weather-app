package io.github.junrdev.openmeteoweatherapp.model

import androidx.annotation.DrawableRes
import io.github.junrdev.openmeteoweatherapp.R

sealed class DailyWeatherForecast(
    @DrawableRes val icon: Int,
    val temperature: String,
    val weather: String,
    val day: String
) {

    data object Tomorrow : DailyWeatherForecast(
        R.drawable.thunderstorm,
        "17",
        "Heavy rain",
        "Tommorrow"
    )

    data object Wednesday : DailyWeatherForecast(
        R.drawable.calmclouds,
        "15",
        "Cloudy",
        "Wednesday"
    )

    data object Thursday : DailyWeatherForecast(
        R.drawable.showers,
        "16",
        "Light Showers",
        "Thursday"
    )


    companion object {
        fun getWeeklyForecast() =
            listOf(
                Tomorrow,
                Wednesday,
                Thursday
            )
    }
}