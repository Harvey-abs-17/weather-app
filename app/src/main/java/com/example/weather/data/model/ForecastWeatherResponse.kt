package com.example.weather.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ForecastWeatherResponse(
    @SerializedName("current")
    var current: Current,
    @SerializedName("forecast")
    var forecast: Forecast,
    @SerializedName("location")
    var location: Location?
) : Parcelable {
    @Parcelize
    data class Current(
        @SerializedName("cloud")
        var cloud: Int, // 50
        @SerializedName("condition")
        var condition: Condition,
        @SerializedName("feelslike_c")
        var feelslikeC: Double, // 20.0
        @SerializedName("feelslike_f")
        var feelslikeF: Double, // 68.0
        @SerializedName("gust_kph")
        var gustKph: Double, // 22.3
        @SerializedName("gust_mph")
        var gustMph: Double, // 13.9
        @SerializedName("humidity")
        var humidity: Int, // 53
        @SerializedName("is_day")
        var isDay: Int, // 1
        @SerializedName("last_updated")
        var lastUpdated: String, // 2023-08-25 17:30
        @SerializedName("last_updated_epoch")
        var lastUpdatedEpoch: Int, // 1692981000
        @SerializedName("precip_in")
        var precipIn: Double, // 0.0
        @SerializedName("precip_mm")
        var precipMm: Double, // 0.0
        @SerializedName("pressure_in")
        var pressureIn: Double, // 29.77
        @SerializedName("pressure_mb")
        var pressureMb: Double, // 1008.0
        @SerializedName("temp_c")
        var tempC: Double, // 20.0
        @SerializedName("temp_f")
        var tempF: Double, // 68.0
        @SerializedName("uv")
        var uv: Double, // 6.0
        @SerializedName("vis_km")
        var visKm: Double, // 10.0
        @SerializedName("vis_miles")
        var visMiles: Double, // 6.0
        @SerializedName("wind_degree")
        var windDegree: Int, // 250
        @SerializedName("wind_dir")
        var windDir: String, // WSW
        @SerializedName("wind_kph")
        var windKph: Double, // 16.9
        @SerializedName("wind_mph")
        var windMph: Double // 10.5
    ) : Parcelable {
        @Parcelize
        data class Condition(
            @SerializedName("code")
            var code: Int, // 1003
            @SerializedName("icon")
            var icon: String, // //cdn.weatherapi.com/weather/64x64/day/116.png
            @SerializedName("text")
            var text: String // Partly cloudy
        ) : Parcelable
    }

    @Parcelize
    data class Forecast(
        @SerializedName("forecastday")
        var forecastday: List<Forecastday>
    ) : Parcelable {
        @Parcelize
        data class Forecastday(
            @SerializedName("astro")
            var astro: Astro,
            @SerializedName("date")
            var date: String, // 2023-08-25
            @SerializedName("date_epoch")
            var dateEpoch: Int, // 1692921600
            @SerializedName("day")
            var day: Day,
            @SerializedName("hour")
            var hour: List<Hour>
        ) : Parcelable {
            @Parcelize
            data class Astro(
                @SerializedName("is_moon_up")
                var isMoonUp: Int, // 0
                @SerializedName("is_sun_up")
                var isSunUp: Int, // 0
                @SerializedName("moon_illumination")
                var moonIllumination: String, // 56
                @SerializedName("moon_phase")
                var moonPhase: String, // Waxing Gibbous
                @SerializedName("moonrise")
                var moonrise: String, // 04:29 PM
                @SerializedName("moonset")
                var moonset: String, // 11:23 PM
                @SerializedName("sunrise")
                var sunrise: String, // 06:01 AM
                @SerializedName("sunset")
                var sunset: String // 08:03 PM
            ) : Parcelable

            @Parcelize
            data class Day(
                @SerializedName("avghumidity")
                var avghumidity: Double, // 57.0
                @SerializedName("avgtemp_c")
                var avgtempC: Double, // 17.7
                @SerializedName("avgtemp_f")
                var avgtempF: Double, // 63.9
                @SerializedName("avgvis_km")
                var avgvisKm: Double, // 10.0
                @SerializedName("avgvis_miles")
                var avgvisMiles: Double, // 6.0
                @SerializedName("condition")
                var condition: Condition,
                @SerializedName("daily_chance_of_rain")
                var dailyChanceOfRain: Int, // 81
                @SerializedName("daily_chance_of_snow")
                var dailyChanceOfSnow: Int, // 0
                @SerializedName("daily_will_it_rain")
                var dailyWillItRain: Int, // 1
                @SerializedName("daily_will_it_snow")
                var dailyWillItSnow: Int, // 0
                @SerializedName("maxtemp_c")
                var maxtempC: Double, // 21.8
                @SerializedName("maxtemp_f")
                var maxtempF: Double, // 71.2
                @SerializedName("maxwind_kph")
                var maxwindKph: Double, // 18.4
                @SerializedName("maxwind_mph")
                var maxwindMph: Double, // 11.4
                @SerializedName("mintemp_c")
                var mintempC: Double, // 15.3
                @SerializedName("mintemp_f")
                var mintempF: Double, // 59.5
                @SerializedName("totalprecip_in")
                var totalprecipIn: Double, // 0.04
                @SerializedName("totalprecip_mm")
                var totalprecipMm: Double, // 1.1
                @SerializedName("totalsnow_cm")
                var totalsnowCm: Double, // 0.0
                @SerializedName("uv")
                var uv: Double// 4.0
            ) : Parcelable {
                @Parcelize
                data class Condition(
                    @SerializedName("code")
                    var code: Int, // 1063
                    @SerializedName("icon")
                    var icon: String, // //cdn.weatherapi.com/weather/64x64/day/176.png
                    @SerializedName("text")
                    var text: String // Patchy rain possible
                ) : Parcelable
            }

            @Parcelize
            data class Hour(
                @SerializedName("chance_of_rain")
                var chanceOfRain: Int, // 81
                @SerializedName("chance_of_snow")
                var chanceOfSnow: Int, // 0
                @SerializedName("cloud")
                var cloud: Int, // 86
                @SerializedName("condition")
                var condition: Condition,
                @SerializedName("dewpoint_c")
                var dewpointC: Double, // 13.1
                @SerializedName("dewpoint_f")
                var dewpointF: Double, // 55.6
                @SerializedName("feelslike_c")
                var feelslikeC: Double, // 16.7
                @SerializedName("feelslike_f")
                var feelslikeF: Double, // 62.1
                @SerializedName("gust_kph")
                var gustKph: Double, // 16.6
                @SerializedName("gust_mph")
                var gustMph: Double, // 10.3
                @SerializedName("heatindex_c")
                var heatindexC: Double, // 16.7
                @SerializedName("heatindex_f")
                var heatindexF: Double, // 62.1
                @SerializedName("humidity")
                var humidity: Int, // 79
                @SerializedName("is_day")
                var isDay: Int, // 0
                @SerializedName("precip_in")
                var precipIn: Double, // 0.0
                @SerializedName("precip_mm")
                var precipMm: Double, // 0.1
                @SerializedName("pressure_in")
                var pressureIn: Double, // 29.82
                @SerializedName("pressure_mb")
                var pressureMb: Double, // 1010.0
                @SerializedName("temp_c")
                var tempC: Double, // 16.7
                @SerializedName("temp_f")
                var tempF: Double, // 62.1
                @SerializedName("time")
                var time: String, // 2023-08-25 00:00
                @SerializedName("time_epoch")
                var timeEpoch: Int, // 1692918000
                @SerializedName("uv")
                var uv: Double, // 1.0
                @SerializedName("vis_km")
                var visKm: Double, // 10.0
                @SerializedName("vis_miles")
                var visMiles: Double, // 6.0
                @SerializedName("will_it_rain")
                var willItRain: Int, // 1
                @SerializedName("will_it_snow")
                var willItSnow: Int, // 0
                @SerializedName("wind_degree")
                var windDegree: Int, // 340
                @SerializedName("wind_dir")
                var windDir: String, // NNW
                @SerializedName("wind_kph")
                var windKph: Double, // 10.4
                @SerializedName("wind_mph")
                var windMph: Double, // 6.5
                @SerializedName("windchill_c")
                var windchillC: Double, // 16.7
                @SerializedName("windchill_f")
                var windchillF: Double // 62.1
            ) : Parcelable {
                @Parcelize
                data class Condition(
                    @SerializedName("code")
                    var code: Int?, // 1063
                    @SerializedName("icon")
                    var icon: String?, // //cdn.weatherapi.com/weather/64x64/night/176.png
                    @SerializedName("text")
                    var text: String? // Patchy rain possible
                ) : Parcelable
            }
        }
    }

    @Parcelize
    data class Location(
        @SerializedName("country")
        var country: String?, // United Kingdom
        @SerializedName("lat")
        var lat: Double?, // 51.52
        @SerializedName("localtime")
        var localtime: String?, // 2023-08-25 17:43
        @SerializedName("localtime_epoch")
        var localtimeEpoch: Int?, // 1692981807
        @SerializedName("lon")
        var lon: Double?, // -0.11
        @SerializedName("name")
        var name: String?, // London
        @SerializedName("region")
        var region: String?, // City of London, Greater London
        @SerializedName("tz_id")
        var tzId: String? // Europe/London
    ) : Parcelable
}