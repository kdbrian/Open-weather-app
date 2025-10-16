package io.github.junrdev.openmeteoweatherapp.data.remote

import io.github.junrdev.openmeteoweatherapp.domain.model.CurrentWeatherForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherAPIRepository @Inject constructor(
    private val weatherService: OpenMeteoWeatherService
) {

    suspend fun getCurrentUpdates(
        longitude: Double?,
        latitude: Double?,
    ): Result<CurrentWeatherForecast> = withContext(Dispatchers.IO) {
        try {
            val response = async {
                if (latitude != null && longitude != null)
                    weatherService.getCurrentUpdates(latitude = latitude, longitude = longitude)
                else
                    weatherService.getCurrentUpdates()
            }.await()

            if (!response.isSuccessful || response.body() == null) {
                Result.failure(
                    Exception(
                        response.errorBody()?.string() ?: "Something unexpected happened"
                    )
                )
            } else
                Result.success(response.body()!!)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

}