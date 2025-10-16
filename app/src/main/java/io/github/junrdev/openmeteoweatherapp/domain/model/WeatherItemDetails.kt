package io.github.junrdev.openmeteoweatherapp.domain.model

import android.health.connect.datatypes.units.Percentage
import androidx.annotation.DrawableRes
import io.github.junrdev.openmeteoweatherapp.R

sealed class WeatherItemDetails(
    @DrawableRes val icon: Int,
    val title: String,
    val levelOrPercentage: String
){
    data object Temperature : WeatherItemDetails(R.drawable.brightsun, "Temperature", "17c")
    data object Rain : WeatherItemDetails(R.drawable.thunderstorm, "Rain", "0")
    data object Showers : WeatherItemDetails(R.drawable.showers, "Showers", "0")

    companion object{

        fun getWeatherItemDetails() = listOf(
            Temperature, Rain, Showers
        )
    }
}
