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

    private lateinit var binding: FragmentDetailBinding
    private val arg: DetailFragmentArgs by navArgs()

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
        presenter.getCurrentWeatherPresenter(arg.location)
        presenter.getForecastWeatherPresenter(arg.location, 1)
        binding.apply {
            nextDaysTxtTitle.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToForecastFragment(arg.location))
            }
        }
    }

    override fun loadCurrentWeather(currentWeather: CurrentWeatherResponse) {
        binding.apply {
            locationName.text = currentWeather.location?.name
            timeTxt.text = currentWeather.location?.localtime
            weatherImage.setImageDrawable(
                specifyWeatherImageAndColor(
                    currentWeather.current?.condition?.text!!,
                    currentWeather.current!!.isDay!!,
                    requireContext()
                )
            )
            weatherSituationTxt.text = currentWeather.current?.condition?.text
            currentTempTxt.text = currentWeather.current?.feelslikeC?.roundToInt().toString()
            windSpeedTxt.text = "${currentWeather.current?.windMph.toString()} mph"
            humidityTxt.text = "${currentWeather.current?.humidity.toString()} %"
            windDirectionTxt.text = currentWeather.current?.windDir

        }
    }

    override fun loadWeatherChart(forecastData: ForecastWeatherResponse) {

        val entries: ArrayList<Entry> = arrayListOf()
        val times = listOf<String>("Morning", "Afternoon", "Evening", "Night")
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

//        val xAxis: XAxis = binding.chart.xAxis
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
////        xAxis.setDrawGridLines(false)
////        xAxis.granularity = 1f
////        xAxis.isGranularityEnabled = false
//        binding.chart.xAxis.valueFormatter = IndexAxisValueFormatter(times)

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

    override fun showLoading(shown: Boolean) {
        binding.apply {
            if (shown) {
                detailProgressBar.visibility = View.VISIBLE
            } else {
                detailProgressBar.visibility = View.GONE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }


}