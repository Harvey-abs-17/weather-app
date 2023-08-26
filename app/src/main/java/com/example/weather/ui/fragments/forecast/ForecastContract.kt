package com.example.weather.ui.fragments.forecast

import com.example.weather.data.model.ForecastWeatherResponse
import com.example.weather.ui.base.BasePresenter
import com.example.weather.ui.base.BaseView

interface ForecastContract {

    interface View : BaseView {

        fun loadDaysForecastWeather(forecastWeather: ForecastWeatherResponse)

    }

    interface Presenter : BasePresenter {

        fun getDaysForecastWeatherPresenter(location :String, days :Int)

    }
}