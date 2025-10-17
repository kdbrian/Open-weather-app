package com.kdbrian.openmeteoweatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class HourlyUpdates(
    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,

    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Double,

    @SerializedName("timezone")
    val timeZone: String,

    @SerializedName("timezone_abbreviation")
    val timeZoneAbbreviation: String,

    @SerializedName("elevation")
    val elevation: String,

    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnits,

    @SerializedName("hourly")
    val hourly: Hourly
)


data class Hourly(
    @SerializedName("time")
    var time: Set<String>,

    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,

    @SerializedName("cloud_cover")
    val cloudCover: List<Int>,

    @SerializedName("wind_speed_10m")
    val windSpeed: List<Double>
)


data class HourlyUnits(
    @SerializedName("time")
    val time: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("cloud_cover")
    val cloudCover: String,
    @SerializedName("wind_speed_10m")
    val windSpeed: String
)
