package com.kdbrian.openmeteoweatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kdbrian.openmeteoweatherapp.BASE_URL
import com.kdbrian.openmeteoweatherapp.data.remote.OpenMeteoWeatherService
import com.kdbrian.openmeteoweatherapp.data.remote.WeatherAPIRepository
import com.kdbrian.openmeteoweatherapp.data.remote.WeatherApiInterceptor
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object CoreModules {


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(WeatherApiInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


        @Singleton
            @Provides
            fun providesWeatherService(retrofit: Retrofit) : OpenMeteoWeatherService{
                return retrofit.create(OpenMeteoWeatherService::class.java)
            }


}