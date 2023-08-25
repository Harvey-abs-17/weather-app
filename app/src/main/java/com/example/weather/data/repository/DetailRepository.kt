package com.example.weather.data.repository

import com.example.weather.data.server.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices) {

    fun getCurrentWeather(location: String) = api.getCurrentWeather(q = location)

    fun getForecastWeatherRepository(location: String, days: Int) =
        api.getForecastWeather(q = location, days = days)

}