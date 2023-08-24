package com.example.weather.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

class SearchWeatherResponse : ArrayList<SearchWeatherResponse.SearchWeatherResponseItem>(){
    @Parcelize
    data class SearchWeatherResponseItem(
        @SerializedName("country")
        var country: String?, // Netherlands
        @SerializedName("id")
        var id: Int?, // 9003087
        @SerializedName("lat")
        var lat: Double?, // 52.31
        @SerializedName("lon")
        var lon: Double?, // 4.76
        @SerializedName("name")
        var name: String?, // Amsterdam Airport Schiphol
        @SerializedName("region")
        var region: String?, // Amsterdam
        @SerializedName("url")
        var url: String? // amsterdam-airport-schiphol-amsterdam-netherlands
    ) : Parcelable
}