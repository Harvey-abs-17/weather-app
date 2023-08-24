package com.example.weather.ui.fragments.search

import com.example.weather.data.model.SearchWeatherResponse
import com.example.weather.data.model.SearchWeatherResponse.SearchWeatherResponseItem
import com.example.weather.ui.base.BasePresenter

interface SearchContract {

    interface View{
        fun loadLocations(list: List<SearchWeatherResponseItem>)
        fun showEmpty()
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter :BasePresenter{
        fun searchLocationPresenter(q :String)
    }

}