package io.github.junrdev.openmeteoweatherapp

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.views.chart.ChartView
import io.github.junrdev.openmeteoweatherapp.adapter.DailyForecastRecylerAdapter
import io.github.junrdev.openmeteoweatherapp.adapter.WeatherItemDetailsRecylerAdapter
import io.github.junrdev.openmeteoweatherapp.data.remote.WeatherAPIRepository
import io.github.junrdev.openmeteoweatherapp.model.DailyWeatherForecast
import io.github.junrdev.openmeteoweatherapp.model.Hourly
import io.github.junrdev.openmeteoweatherapp.model.WeatherItemDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var precipitationLineChart: ChartView
    lateinit var weatherItemsDetailsRecycler: RecyclerView
    lateinit var weeklyForecastRecycler: RecyclerView
    lateinit var todayTemperature: TextView
    lateinit var todayTemperatureUnits: TextView
    private val LOCATION_PERMISSION_REQUEST_CODE = 101
    lateinit var locationClient: FusedLocationProviderClient
    lateinit var chartOptionsSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestPermissions()
        locationClient = LocationServices.getFusedLocationProviderClient(this)
        weatherItemsDetailsRecycler = findViewById(R.id.todayWeatherDetailsRecycler)
        todayTemperature = findViewById(R.id.todayTemperature)
        todayTemperatureUnits = findViewById(R.id.todayTemperatureUnits)
        weeklyForecastRecycler = findViewById(R.id.weeklyForecastRecycler)
        chartOptionsSpinner = findViewById(R.id.chartOptionsSpinner)

        chartOptionsSpinner.apply {
            adapter = ArrayAdapter.createFromResource(
                applicationContext,
                R.array.chartitems,
                android.R.layout.simple_spinner_dropdown_item
            )
        }


        val detailsRecylerAdapter = WeatherItemDetailsRecylerAdapter(
            applicationContext,
            WeatherItemDetails.getWeatherItemDetails()
        )


        val dailyForecastAdapter = DailyForecastRecylerAdapter(
            applicationContext,
            DailyWeatherForecast.getWeeklyForecast()
        )

        weatherItemsDetailsRecycler.adapter = detailsRecylerAdapter
        weeklyForecastRecycler.adapter = dailyForecastAdapter



        precipitationLineChart = findViewById(R.id.todaysDistributionLineChart)

        val weatherAPIRepository = WeatherAPIRepository()

        CoroutineScope(Dispatchers.IO).launch {
            val updates = weatherAPIRepository.getCurrentUpdates()
            Log.d(TAG, "onCreate: $updates")

            withContext(Dispatchers.Main) {
//                Toast.makeText(applicationContext, "$updates", Toast.LENGTH_SHORT).show()

                val current = updates.current


                updates.apply {

                    todayTemperatureUnits.setText("${currentUnits.temperature2m}")

                    current.apply {
                        todayTemperature.setText("$temperature2m")
                    }

                    val chartOptions = resources.getStringArray(R.array.chartitems) as Array<String>
                    chartOptions.forEachIndexed { index, opt ->
                        Log.d(TAG, "onCreate: $index -> $opt")
                    }
                    hourly.apply {

                        val currentTime = LocalDateTime.now()
                        // filter only today
                        val timers =
                            time.filter { it.split("T")[0] == current.time.split("T")[0] }.toSet()

                        // get hours only
                        var times = timers.map { it.split("T")[1].split(":")[0].toFloat() }
                        val curr = times.indexOfFirst { it == currentTime.hour.toFloat() }
                        if (curr != -1) times = times.subList(curr, times.size)

                        reloadChart(times, temperature2m)
                        chartOptionsSpinner.onItemSelectedListener = SpinnerListener(times, this)

                        //map the values
//                        reloadChart(times, temperature2m)
                    }
                }
            }
        }


    }

    inner class SpinnerListener(val times: List<Float>, val hourly: Hourly) :
        AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            Log.d(TAG, "onItemSelected: $id")

            hourly.apply {

                val yaxesdata: List<Double> = when (id) {
                    0L -> temperature2m
                    1L -> cloudCover.map { it.toDouble() }
                    2L -> windSpeed
                    else -> temperature2m
                }
                Log.d(TAG, "onItemSelected: $yaxesdata")
//                                precipitationLineChart.
                reloadChart(times, yaxesdata)
            }

        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }


    private fun reloadChart(times: List<Float>, temperature2m: List<Double>) {
        val data = times.zip(temperature2m) { x, y -> Pair(x, y) }
        val tempEntries = entryModelOf(
            data.map { FloatEntry(it.first, it.second.toFloat()) }
        )
        precipitationLineChart.elevation = 4f
        precipitationLineChart.setModel(tempEntries)
    }

    private fun checkLocationPermissions(): Boolean {
        return (
                ActivityCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(
                            applicationContext,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                )
    }

    private fun requestPermissions() {
        if (!checkLocationPermissions()) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onLocationChanged(location: Location) {
        Log.d(TAG, "onLocationChanged: lat ->${location.latitude} long -> ${location.longitude}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d(TAG, "onActivityResult: $resultCode")

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && resultCode != -1) {
//            Log.d(TAG, "onActivityResult: $resultCode")
            requestPermissions()
        }
    }
}