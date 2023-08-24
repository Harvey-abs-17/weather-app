package com.example.weather.data.server

import com.example.weather.data.model.SearchWeatherResponse
import com.example.weather.data.model.SearchWeatherResponse.SearchWeatherResponseItem
import com.example.weather.utils.API_KEY
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    @GET("search")
    fun searchLocation(
        @Query("key") key :String = API_KEY,
        @Query("q") q :String
    ) :Observable<List<SearchWeatherResponseItem>>

}