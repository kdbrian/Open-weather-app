package io.github.junrdev.openmeteoweatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.junrdev.openmeteoweatherapp.data.remote.OpenMeteoWeatherService
import io.github.junrdev.openmeteoweatherapp.data.remote.WeatherAPIRepository
import io.github.junrdev.openmeteoweatherapp.domain.usecases.HourlyUpdatesByLatLngUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherUpdatesModule {

    @Singleton
    @Provides
    fun providesWeatherAPIRepository(weatherService: OpenMeteoWeatherService): WeatherAPIRepository {
        return WeatherAPIRepository(
            weatherService = weatherService
        )
    }

    @Singleton
    @Provides
    fun providesHourlyUpdatesByLatLngUseCase(weatherAPIRepository: WeatherAPIRepository): HourlyUpdatesByLatLngUseCase {
        return HourlyUpdatesByLatLngUseCase(
            weatherAPIRepository = weatherAPIRepository
        )
    }


}