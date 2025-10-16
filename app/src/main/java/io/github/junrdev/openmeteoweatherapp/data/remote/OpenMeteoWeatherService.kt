package io.github.junrdev.openmeteoweatherapp.data.remote

import io.github.junrdev.openmeteoweatherapp.domain.model.CurrentWeatherForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoWeatherService {

    // lat -1.042022
    // long 37.080269


    @GET("forecast")
    suspend fun getCurrentUpdates(
        @Query("latitude") latitude: Double = -1.25,
        @Query("longitude") longitude: Double = 38.875,
        @Query("hourly") hourly: String = "temperature_2m,cloud_cover,wind_speed_10m",
        @Query("current") currentParams: String = "temperature_2m,precipitation,rain,showers,cloud_cover,wind_speed_10m"
    ): Response<CurrentWeatherForecast>

}