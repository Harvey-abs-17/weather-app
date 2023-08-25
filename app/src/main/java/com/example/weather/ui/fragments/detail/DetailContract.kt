package com.example.weather.ui.fragments.detail

import com.example.weather.data.model.CurrentWeatherResponse
import com.example.weather.data.model.ForecastWeatherResponse
import com.example.weather.ui.base.BaseView

interface DetailContract {

    interface View : BaseView {
        fun loadCurrentWeather(currentWeather: CurrentWeatherResponse)
        fun loadWeatherChart(forecastData: ForecastWeatherResponse)
        fun loadForecastRec(forecastData: ForecastWeatherResponse)
    }

    interface Presenter {
        fun getCurrentWeatherPresenter(location: String)
        fun getForecastWeatherPresenter(location: String, days: Int)
    }


}