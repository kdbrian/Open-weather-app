package io.github.junrdev.openmeteoweatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherForecast(
    @SerializedName("latitude") val latitude: Double,

    @SerializedName("longitude") val longitude: Double,

    @SerializedName("generationtime_ms") val generationTimeMs: Double,

    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Double,

    @SerializedName("timezone") val timeZone: String,

    @SerializedName("timezone_abbreviation") val timeZoneAbbreviation: String,

    @SerializedName("elevation") val elevation: String,

    @SerializedName("current_units") val currentUnits: CurrentUnits,

    @SerializedName("current") val current: Current,

    @SerializedName("hourly_units") val hourlyUnits: HourlyUnits,

    @SerializedName("hourly") val hourly: Hourly
)

data class Current(
    @SerializedName("time")
    val time: String,

    @SerializedName("interval")
    val interval: Double,

    @SerializedName("temperature_2m")
    val temperature2m: Double,

    @SerializedName("precipitation")
    val precipitation: Double,

    @SerializedName("rain")
    val rain: Double,

    @SerializedName("showers")
    val showers: Double,

    @SerializedName("cloud_cover")
    val cloudCover: Double,

    @SerializedName("wind_speed_10m")
    val windSpeed: Double
)

data class CurrentUnits(
    @SerializedName("time")
    val time: String,
    @SerializedName("interval")
    val interval: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("precipitation")
    val precipitation: String,
    @SerializedName("rain")
    val rain: String,
    @SerializedName("showers")
    val showers: String,
    @SerializedName("cloud_cover")
    val cloudCover: String,
    @SerializedName("wind_speed_10m")
    val windSpeed: String
)


