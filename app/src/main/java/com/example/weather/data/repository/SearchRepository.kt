package com.example.weather.data.repository

import com.example.weather.data.server.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor( private val api :ApiServices ){

    fun searchLocationRepository(location :String) = api.searchLocation(q = location)

}