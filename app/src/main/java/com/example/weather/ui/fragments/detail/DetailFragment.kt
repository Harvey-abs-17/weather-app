package com.example.weather.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.CurrentWeatherResponse
import com.example.weather.data.model.ForecastWeatherResponse
import com.example.weather.databinding.FragmentDetailBinding
import com.example.weather.ui.fragments.detail.adapter.HourForecastAdapter
import com.example.weather.utils.specifyWeatherImageAndColor
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt


@AndroidEntryPoint
class DetailFragment : Fragment(), DetailContract.View {

    //binding
    private lateinit var binding: FragmentDetailBinding
    //fragment arg
    private val arg: DetailFragmentArgs by navArgs()

    // inject dependencies
    @Inject
    lateinit var presenter: DetailPresenter
    @Inject
    lateinit var forecastAdapter: HourForecastAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get current weather
        presenter.getCurrentWeatherPresenter(arg.location)
        //get forecast weather
        presenter.getForecastWeatherPresenter(arg.location, 1)
        binding.apply {
            //go to forecast fragment
            nextDaysTxtTitle.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToForecastFragment(arg.location))
            }
            // go back
            detailBackBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    //load current weather
    override fun loadCurrentWeather(currentWeather: CurrentWeatherResponse) {
        binding.apply {
            locationName.text = currentWeather.location?.name
            timeTxt.text = currentWeather.location?.localtime
            weatherSituationTxt.text = currentWeather.current?.condition?.text
            currentTempTxt.text = currentWeather.current?.feelslikeC?.roundToInt().toString()
            windSpeedTxt.text = "${currentWeather.current?.windMph.toString()} mph"
            humidityTxt.text = "${currentWeather.current?.humidity.toString()} %"
            windDirectionTxt.text = currentWeather.current?.windDir
            detailActionBarTitle.text = currentWeather.location?.name
            weatherImage.setImageDrawable(
                specifyWeatherImageAndColor(
                    currentWeather.current?.condition?.text!!,
                    currentWeather.current!!.isDay!!,
                    requireContext()
                )
            )
        }
    }

    //load chart weather
    override fun loadWeatherChart(forecastData: ForecastWeatherResponse) {
        // create list of chart data set
        val entries: ArrayList<Entry> = arrayListOf()
        for ((i, item) in forecastData.forecast.forecastday[0].hour.withIndex()) {
            if (i % 6 == 0) {
                entries.add(
                    Entry(
                        item.time.substring(10, 13).toFloat(),
                        item.feelslikeC.roundToInt().toFloat()
                    )
                )
            }
        }
        val dataSet = LineDataSet(entries, "Label")
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.text_day_color)
        val lineData = LineData(dataSet)

        // bind chart view
        binding.apply {
            chart.data = lineData
            chart.axisLeft.setDrawLabels(false)
            chart.axisRight.setDrawLabels(false)
            chart.xAxis.setDrawLabels(false)
            chart.description = null
            chart.legend.isEnabled = false
            chart.invalidate()
        }
    }

    //load forecast rec data
    override fun loadForecastRec(forecastData: ForecastWeatherResponse) {
        binding.apply {
            forecastAdapter.setData(forecastData.forecast.forecastday[0].hour)
            todayRec.apply {
                adapter = forecastAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
        }
    }

    //manage loading progress bar
    override fun showLoading(shown: Boolean) {
        binding.apply {
            if (shown) {
                detailProgressBar.visibility = View.VISIBLE
                mainLayout.visibility = View.GONE
            } else {
                detailProgressBar.visibility = View.GONE
                mainLayout.visibility = View.VISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }


}