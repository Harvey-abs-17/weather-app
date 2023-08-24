package com.example.weather.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.SearchWeatherResponse
import com.example.weather.databinding.FragmentSearchBinding
import com.example.weather.ui.fragments.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchContract.View {

    private lateinit var binding :FragmentSearchBinding

    @Inject
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var searchAdapter :SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            searchEdtTxt.addTextChangedListener {
                presenter.searchLocationPresenter(it.toString())
            }
        }
    }

    override fun loadLocations(list: List<SearchWeatherResponse.SearchWeatherResponseItem>) {
        binding.apply {
            searchAdapter.setData(list)
            searchRec.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            }
        }
    }

    override fun showEmpty() {
        binding.emptyLayout.visibility = View.VISIBLE
    }

    override fun showLoading() {
        binding.searchProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.searchProgressBar.visibility = View.GONE
    }
}