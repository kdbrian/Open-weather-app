package com.kdbrian.openmeteoweatherapp.domain.usecases

import com.kdbrian.openmeteoweatherapp.data.remote.OpenMeteoWeatherService
import com.kdbrian.openmeteoweatherapp.data.remote.WeatherAPIRepository

class HourlyUpdatesByLatLngUseCase(
    private val weatherAPIRepository: WeatherAPIRepository
) {
    suspend fun invoke(
        longitude: Double?,
        latitude: Double?,
    ) = weatherAPIRepository.getCurrentUpdates(
        latitude = latitude,
        longitude = longitude
    )
}