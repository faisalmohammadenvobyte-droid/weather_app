package com.openkeyboard.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openkeyboard.myapplication.domain.model.Weather
import com.openkeyboard.myapplication.domain.usecase.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WeatherUiState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null
)

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun fetchWeather(lat: Double, lon: Double, apiKey: String) {
        _uiState.value = WeatherUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val weather = getWeatherDataUseCase(lat, lon, apiKey)
                _uiState.value = WeatherUiState(weather = weather)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState(error = e.message ?: "Unknown error")
            }
        }
    }

}