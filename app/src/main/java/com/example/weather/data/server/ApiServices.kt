package com.example.weather.data.server

import com.example.weather.data.model.CurrentWeatherResponse
import com.example.weather.data.model.ForecastWeatherResponse
import com.example.weather.data.model.SearchWeatherResponse.SearchWeatherResponseItem
import com.example.weather.utils.API_KEY
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    @GET("search.json")
    fun searchLocation(
        @Query("key") key: String = API_KEY,
        @Query("q") q: String
    ): Observable<List<SearchWeatherResponseItem>>

    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") key: String = API_KEY,
        @Query("q") q: String
    ): Observable<CurrentWeatherResponse>

    @GET("forecast.json")
    fun getForecastWeather(
        @Query("key") key: String = API_KEY,
        @Query("q") q: String,
        @Query("days") days: Int
    ): Observable<ForecastWeatherResponse>

}