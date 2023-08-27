package com.example.weather.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.SearchWeatherResponse
import com.example.weather.databinding.FragmentSearchBinding
import com.example.weather.ui.fragments.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchContract.View {

    // binding
    private lateinit var binding: FragmentSearchBinding

    //inject dependencies
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
            searchEmptyLayout.visibility = View.VISIBLE

            // get data from edit text
            searchEdtTxt.addTextChangedListener {
                if (it.toString().isNotEmpty()){
                    presenter.searchLocationPresenter(it.toString())
                    searchEmptyLayout.visibility = View.GONE
                }
                else{
                    searchEmptyLayout.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                }
            }
        }
    }

    // load location rec data
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
            // manage click listener on rec item
            searchAdapter.itemClickListener {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                        it
                    )
                )
                //set edit text null after click on item
                searchEdtTxt.text = null
            }
        }
    }

    // manage empty layout
    override fun showEmpty() {
        binding.apply {
            emptyLayout.visibility = View.VISIBLE
            searchRec.visibility = View.GONE
        }
    }

    //manage loading progress bar
    override fun showLoading(shown: Boolean) {
        binding.apply {
            if (shown) {
                searchProgressBar.visibility = View.VISIBLE
            } else {
                searchProgressBar.visibility = View.GONE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }


}