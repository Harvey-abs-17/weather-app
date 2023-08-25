package com.example.weather.ui.fragments.detail.adapter

import com.robinhood.spark.SparkAdapter

class ChartAdapter (val data : List<Double>) :SparkAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(index: Int): Double {
        return data[index]
    }

    override fun getY(index: Int): Float {
        return data[index].toFloat()
    }
}