package io.github.junrdev.openmeteoweatherapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.junrdev.openmeteoweatherapp.R
import io.github.junrdev.openmeteoweatherapp.model.DailyWeatherForecast

private const val TAG = "DailyForecastRecyler"


class DailyForecastRecylerAdapter(
    val context: Context,
    val dailyWeatherForecasts: List<DailyWeatherForecast>
) : RecyclerView.Adapter<DailyForecastRecylerAdapter.VH>() {

    init {
        Log.d(TAG, "items : ${dailyWeatherForecasts.size}")
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val forecastTemperature: TextView = itemView.findViewById(R.id.forecastTemperature)
        val weather: TextView = itemView.findViewById(R.id.weather)
        val day: TextView = itemView.findViewById(R.id.day)
        val illustration: ImageView = itemView.findViewById(R.id.illustration)

        fun bind(dailyWeatherForecast: DailyWeatherForecast) {
            forecastTemperature?.setText(dailyWeatherForecast.temperature)
            weather?.setText(dailyWeatherForecast.weather)
            day?.setText(dailyWeatherForecast.day)
            illustration?.setImageDrawable(context.getDrawable(dailyWeatherForecast.icon))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.dailyforecastreport, parent, false)
        )
    }

    override fun getItemCount(): Int = dailyWeatherForecasts.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val forecast = dailyWeatherForecasts[position]
        holder.bind(forecast)
    }
}