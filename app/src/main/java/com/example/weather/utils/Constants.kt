package com.example.weather.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.weather.R

const val API_KEY = "e11ad0e28919418ba4a215839232207"
const val BASE_URL = "http://api.weatherapi.com/v1/"
const val TIME_OUT = 60L
const val BODY_LOGGING_INTERCEPTOR = "body"
const val HEADER_LOGGING_INTERCEPTOR = "header"

fun specifyWeatherImageAndColor(weatherCondition: String, isDay: Int, context: Context): Drawable? {
    return when (isDay) {
        1 -> {
            when (weatherCondition) {
                "clear" -> {
                    ContextCompat.getDrawable(context, R.drawable.sunny_day)
                }

                "Partly cloudy" -> {
                    ContextCompat.getDrawable(context, R.drawable.day_partly_cloud)
                }

                "Light rain" -> {
                    ContextCompat.getDrawable(context, R.drawable.day_rain_cloud)
                }

                "Rain" -> {
                    ContextCompat.getDrawable(context, R.drawable.rain)
                }

                "Overcast" -> {
                    ContextCompat.getDrawable(context, R.drawable.cloudy)
                }

                "Mist" -> {
                    ContextCompat.getDrawable(context, R.drawable.cloudy)
                }

                "Fog" -> {
                    ContextCompat.getDrawable(context, R.drawable.fog)
                }

                "Light drizzle" -> {
                    ContextCompat.getDrawable(context, R.drawable.rain)
                }

                "Moderate rain" -> {
                    ContextCompat.getDrawable(context, R.drawable.rain)
                }

                "Light rain shower" -> {
                    ContextCompat.getDrawable(context, R.drawable.day_rain_cloud)
                }

                "Patchy rain possible" -> {
                    ContextCompat.getDrawable(context, R.drawable.day_rain_cloud)
                }

                else -> {
                    ContextCompat.getDrawable(context, R.drawable.sunny_day)
                }
            }
        }

        0 -> {
            when (weatherCondition) {
                "clear" -> {
                    ContextCompat.getDrawable(context, R.drawable.night)
                }

                "Partly cloudy" -> {
                    ContextCompat.getDrawable(context, R.drawable.night_partly_cloud)
                }

                "Light rain" -> {
                    ContextCompat.getDrawable(context, R.drawable.night_rain_cloud)
                }

                "Rain" -> {
                    ContextCompat.getDrawable(context, R.drawable.rain)
                }

                "Overcast" -> {
                    ContextCompat.getDrawable(context, R.drawable.cloudy)
                }

                "Mist" -> {
                    ContextCompat.getDrawable(context, R.drawable.cloudy)
                }

                "Fog" -> {
                    ContextCompat.getDrawable(context, R.drawable.fog)
                }

                "Light drizzle" -> {
                    ContextCompat.getDrawable(context, R.drawable.rain)
                }

                "Moderate rain" -> {
                    ContextCompat.getDrawable(context, R.drawable.rain)
                }

                "Light rain shower" -> {
                    ContextCompat.getDrawable(context, R.drawable.night_rain_cloud)
                }

                "Patchy rain possible" -> {
                    ContextCompat.getDrawable(context, R.drawable.night_rain_cloud)
                }

                else -> {
                    ContextCompat.getDrawable(context, R.drawable.night)
                }
            }
        }

        else -> {
            ContextCompat.getDrawable(context, R.drawable.sunny_day)
        }
    }
}
