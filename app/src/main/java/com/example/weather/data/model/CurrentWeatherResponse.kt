package com.example.weather.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CurrentWeatherResponse(
    @SerializedName("current")
    var current: Current?,
    @SerializedName("location")
    var location: Location?
) : Parcelable {
    @Parcelize
    data class Current(
        @SerializedName("cloud")
        var cloud: Int?, // 50
        @SerializedName("condition")
        var condition: Condition?,
        @SerializedName("feelslike_c")
        var feelslikeC: Double?, // 17.0
        @SerializedName("feelslike_f")
        var feelslikeF: Double?, // 62.6
        @SerializedName("gust_kph")
        var gustKph: Double?, // 14.4
        @SerializedName("gust_mph")
        var gustMph: Double?, // 8.9
        @SerializedName("humidity")
        var humidity: Int?, // 83
        @SerializedName("is_day")
        var isDay: Int?, // 0
        @SerializedName("last_updated")
        var lastUpdated: String?, // 2023-08-25 00:45
        @SerializedName("last_updated_epoch")
        var lastUpdatedEpoch: Int?, // 1692920700
        @SerializedName("precip_in")
        var precipIn: Double?, // 0.0
        @SerializedName("precip_mm")
        var precipMm: Double?, // 0.0
        @SerializedName("pressure_in")
        var pressureIn: Double?, // 29.83
        @SerializedName("pressure_mb")
        var pressureMb: Double?, // 1010.0
        @SerializedName("temp_c")
        var tempC: Double?, // 17.0
        @SerializedName("temp_f")
        var tempF: Double?, // 62.6
        @SerializedName("uv")
        var uv: Double?, // 1.0
        @SerializedName("vis_km")
        var visKm: Double?, // 10.0
        @SerializedName("vis_miles")
        var visMiles: Double?, // 6.0
        @SerializedName("wind_degree")
        var windDegree: Int?, // 320
        @SerializedName("wind_dir")
        var windDir: String?, // NW
        @SerializedName("wind_kph")
        var windKph: Double?, // 9.0
        @SerializedName("wind_mph")
        var windMph: Double? // 5.6
    ) : Parcelable {
        @Parcelize
        data class Condition(
            @SerializedName("code")
            var code: Int?, // 1003
            @SerializedName("icon")
            var icon: String?, // //cdn.weatherapi.com/weather/64x64/night/116.png
            @SerializedName("text")
            var text: String? // Partly cloudy
        ) : Parcelable
    }

    @Parcelize
    data class Location(
        @SerializedName("country")
        var country: String?, // United Kingdom
        @SerializedName("lat")
        var lat: Double?, // 51.52
        @SerializedName("localtime")
        var localtime: String?, // 2023-08-25 0:55
        @SerializedName("localtime_epoch")
        var localtimeEpoch: Int?, // 1692921312
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