package com.example.weather.ui.fragments.search

import android.database.Observable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.SearchWeatherResponse
import com.example.weather.databinding.FragmentSearchBinding
import com.example.weather.ui.fragments.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchContract.View {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var searchAdapter: SearchAdapter

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
            searchRec.visibility = View.VISIBLE
            emptyLayout.visibility = View.GONE
            searchAdapter.setData(list)
            searchRec.apply {
                adapter = searchAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            }
            searchAdapter.itemClickListener {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(it))
            }
        }
    }

    override fun showEmpty() {
        binding.apply {
            emptyLayout.visibility = View.VISIBLE
            searchRec.visibility = View.GONE
        }
    }

    override fun showLoading(shown: Boolean) {
        binding.apply {
            if (shown) {
                searchProgressBar.visibility = View.VISIBLE
            } else {
                searchProgressBar.visibility = View.GONE
            }
        }
    }


}