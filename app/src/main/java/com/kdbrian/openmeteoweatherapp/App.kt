package com.kdbrian.openmeteoweatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

const val BASE_URL = "https://api.open-meteo.com/v1/"

@HiltAndroidApp
class App : Application()