package com.example.weather.ui.fragments.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.SearchWeatherResponse.SearchWeatherResponseItem
import com.example.weather.databinding.ItemSearchWeatherBinding
import javax.inject.Inject

class SearchAdapter @Inject constructor() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private lateinit var binding: ItemSearchWeatherBinding
    private var oldItem: List<SearchWeatherResponseItem> = emptyList()

    inner class SearchViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(item: SearchWeatherResponseItem) {
            binding.search = item
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(item.name.toString())
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_search_weather,
            parent,
            false
        )
        return SearchViewHolder()
    }

    override fun getItemCount(): Int {
        return oldItem.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindViews(oldItem[position])
        holder.isRecyclable
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private var onItemClickListener: ((location :String) -> Unit)? = null
    fun itemClickListener( listener :(location :String) -> Unit){
        onItemClickListener = listener
    }

    fun setData(searchList: List<SearchWeatherResponseItem>) {
        val differ = DifferCallback(oldItem, searchList)
        val differCallback = DiffUtil.calculateDiff(differ)
        oldItem = searchList
        differCallback.dispatchUpdatesTo(this)
    }


    class DifferCallback(
        private val oldList: List<SearchWeatherResponseItem>,
        private val newList: List<SearchWeatherResponseItem>
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