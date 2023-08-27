package com.example.weather.ui.fragments.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.ForecastWeatherResponse.Forecast.Forecastday.*
import com.example.weather.databinding.ItemWeatherForecastBinding
import com.example.weather.utils.specifyWeatherImageAndColor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.math.roundToInt

class HourForecastAdapter @Inject constructor( @ApplicationContext private val context :Context) :RecyclerView.Adapter<HourForecastAdapter.ForecastViewHolder>() {

    //binding
    private lateinit var binding :ItemWeatherForecastBinding
    // forecast list
    private var forecastList = emptyList<Hour>()

    inner class ForecastViewHolder :RecyclerView.ViewHolder( binding.root ){

        fun bindViews(item : Hour){
            binding.apply {
                todayTimeTxt.text = item.time.substring(10)
                currentTempTxt.text = item.tempC.roundToInt().toString()
                weatherImage.setImageDrawable(
                    specifyWeatherImageAndColor(
                        item.condition.text!!,
                        item.isDay,
                        context
                    )
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        binding = ItemWeatherForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder()
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bindViews(forecastList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    //set list data
    fun setData(newItemList :List<Hour>){
        val differCallback = DifferCallback(forecastList, newItemList)
        val differ = DiffUtil.calculateDiff(differCallback)
        forecastList = newItemList
        differ.dispatchUpdatesTo(this)
    }

    //differ callback
    class DifferCallback(private val oldList :List<Hour>, private val newList :List<Hour>) :DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList == newList
        }

    }
}