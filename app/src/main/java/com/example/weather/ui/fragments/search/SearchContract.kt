package com.example.weather.ui.fragments.search

import com.example.weather.data.model.SearchWeatherResponse
import com.example.weather.data.model.SearchWeatherResponse.SearchWeatherResponseItem
import com.example.weather.ui.base.BasePresenter
import com.example.weather.ui.base.BaseView

interface SearchContract {

    interface View :BaseView{
        fun loadLocations(list: List<SearchWeatherResponseItem>)
        fun showEmpty()
    }

    interface Presenter :BasePresenter{
        fun searchLocationPresenter(q :String)
    }

}