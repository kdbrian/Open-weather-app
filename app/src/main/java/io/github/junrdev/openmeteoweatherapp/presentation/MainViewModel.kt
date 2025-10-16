package io.github.junrdev.openmeteoweatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.junrdev.openmeteoweatherapp.domain.model.Current
import io.github.junrdev.openmeteoweatherapp.domain.usecases.HourlyUpdatesByLatLngUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MainScreenUiState(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val locationName: String? = null,
    val currentWeather: Current? = null
)


@HiltViewModel
class MainViewModel @Inject constructor(
    private val updatesByLatLngUseCase: HourlyUpdatesByLatLngUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun listenForUpdatesByLatLngUseCase() {
        viewModelScope.launch {
            updatesByLatLngUseCase
                .invoke(
                    latitude = _uiState.value.latitude,
                    longitude = _uiState.value.longitude
                ).onSuccess { forecast ->
                    _uiState.update {
                        it.copy(
                            currentWeather = forecast.current
                        )
                    }


                }

        }

    }

}