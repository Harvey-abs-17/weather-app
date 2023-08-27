package com.example.weather.data.repository

import com.example.weather.data.server.ApiServices
import javax.inject.Inject

class ForecastRepository @Inject constructor(private val api: ApiServices) {
    fun getDaysForecastWeatherRepository(location: String, days: Int) = api.getForecastWeather(q = location, days = days)
}