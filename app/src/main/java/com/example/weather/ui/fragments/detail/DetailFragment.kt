package com.example.weather.ui.fragments.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.CurrentWeatherResponse
import com.example.weather.data.model.ForecastWeatherResponse
import com.example.weather.databinding.FragmentDetailBinding
import com.example.weather.ui.fragments.detail.adapter.ChartAdapter
import com.example.weather.ui.fragments.detail.adapter.ForecastAdapter
import com.example.weather.utils.specifyWeatherImageAndColor
import com.robinhood.spark.SparkAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment : Fragment(), DetailContract.View {

    private lateinit var binding: FragmentDetailBinding
    private val arg: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var presenter: DetailPresenter

    @Inject
    lateinit var forecastAdapter: ForecastAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getCurrentWeatherPresenter(arg.location)
        presenter.getForecastWeatherPresenter(arg.location, 1)
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
            currentTempTxt.text = currentWeather.current?.feelslikeC.toString()
            windSpeedTxt.text = currentWeather.current?.windMph.toString()
            humidityTxt.text = currentWeather.current?.humidity.toString()
            windDirectionTxt.text = currentWeather.current?.windDir

        }
    }

    override fun loadWeatherChart(forecastData: ForecastWeatherResponse) {

        val chartData = listOf(
            forecastData.forecast?.forecastday!![0]?.hour!![1]?.tempC!!,
            forecastData.forecast?.forecastday!![0]?.hour!![2]?.tempC!!,
            forecastData.forecast?.forecastday!![0]?.hour!![3]?.tempC!!,
            forecastData.forecast?.forecastday!![0]?.hour!![4]?.tempC!!,
        )
        binding.sparkview.adapter = ChartAdapter(chartData)
    }

    override fun loadForecastRec(forecastData: ForecastWeatherResponse) {
        binding.apply {
        forecastAdapter.setData(forecastData.forecast?.forecastday!![0].hour)
            todayRec.apply {
                adapter = forecastAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
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


}