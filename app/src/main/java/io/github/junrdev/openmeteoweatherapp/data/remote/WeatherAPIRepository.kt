package io.github.junrdev.openmeteoweatherapp.data.remote

import io.github.junrdev.openmeteoweatherapp.model.CurrentWeatherForecast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIRepository {

    private val BASE_URL = "https://api.open-meteo.com/v1/"
    val scope = CoroutineScope(Dispatchers.IO)

    val client = OkHttpClient.Builder()
        .addInterceptor(WeatherApiInterceptor())
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val openMeteoWeatherService = retrofit.create(OpenMeteoWeatherService::class.java)

    suspend fun getCurrentUpdates(oneError: ((errorMessage: String) -> Unit)? = null): CurrentWeatherForecast {
        val response = openMeteoWeatherService.getCurrentUpdates()

        if (!response.isSuccessful) {
            oneError?.invoke(response.errorBody()!!.string())
        }

        return response?.body()!!
    }

}