package io.github.junrdev.openmeteoweatherapp.domain.usecases

import io.github.junrdev.openmeteoweatherapp.data.remote.OpenMeteoWeatherService
import io.github.junrdev.openmeteoweatherapp.data.remote.WeatherAPIRepository

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