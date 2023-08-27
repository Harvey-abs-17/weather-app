package com.example.weather.ui.fragments.forecast.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.ForecastWeatherResponse.Forecast
import com.example.weather.databinding.ItemWeatherForecastBinding
import com.example.weather.utils.specifyWeatherImageAndColor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.math.roundToInt

class DayForecastAdapter @Inject constructor( @ApplicationContext private val context :Context ) :
    RecyclerView.Adapter<DayForecastAdapter.DayForecastViewHolder>() {

    //binding
    private lateinit var binding: ItemWeatherForecastBinding

    //forecast list
    private var forecastList = emptyList<Forecast.Forecastday>()


    inner class DayForecastViewHolder : RecyclerView.ViewHolder(binding.root) {

        //bind views data
        fun bindView(item :Forecast.Forecastday, position: Int){
            binding.apply {
                todayTimeTxt.text = item.date.substring(5)
                currentTempTxt.text = item.day.avgtempC.roundToInt().toString()
                weatherImage.setImageDrawable(
                    specifyWeatherImageAndColor(
                        item.day.condition.text,
                        1,
                        context
                    )
                )
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastViewHolder {
        binding =
            ItemWeatherForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayForecastViewHolder()
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: DayForecastViewHolder, position: Int) {
        holder.bindView(forecastList[position], position)
        //set first item as default
        if (position == 0){
            holder.itemView.performClick()
        }
    }

    private var onItemClickListener :((Forecast.Forecastday) -> Unit)? = null
    fun itemClickListener( listener :(Forecast.Forecastday) -> Unit ){
        onItemClickListener = listener
    }

    //set list data
    fun setData(list: List<Forecast.Forecastday>) {
        val differCallback = DifferCallback(forecastList, list)
        val differ = DiffUtil.calculateDiff(differCallback)
        forecastList = list
        differ.dispatchUpdatesTo(this)
    }

    //differ callback
    class DifferCallback(
        private val oldList: List<Forecast.Forecastday>,
        private val newList: List<Forecast.Forecastday>
    ) : DiffUtil.Callback() {
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