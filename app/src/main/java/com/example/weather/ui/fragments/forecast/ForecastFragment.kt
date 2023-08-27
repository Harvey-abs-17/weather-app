package com.example.weather.ui.fragments.forecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.ForecastWeatherResponse
import com.example.weather.databinding.FragmentForecastBinding
import com.example.weather.ui.fragments.forecast.adapter.DayForecastAdapter
import com.example.weather.utils.specifyWeatherImageAndColor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class ForecastFragment : Fragment(), ForecastContract.View {

    //binding
    private lateinit var binding: FragmentForecastBinding

    //fragment arg
    private val forecastArgs: ForecastFragmentArgs by navArgs()

    //inject dependencies
    @Inject
    lateinit var forecastAdapter :DayForecastAdapter
    @Inject
    lateinit var presenter :ForecastPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForecastBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get 3 days forecast weather
        presenter.getDaysForecastWeatherPresenter(forecastArgs.location, 3)
        //bind detail weather data for each item
        bindDetailForecastViews()
        // back btn
        binding.forecastBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    //bind detail weather data for each item
    private fun bindDetailForecastViews(){
        binding.apply {
            forecastAdapter.itemClickListener {
                //image
                weatherImage.setImageDrawable(
                    specifyWeatherImageAndColor(
                        it.day.condition.text,
                        1,
                        requireContext()
                    )
                )
                weatherSituationTxt.text = it.day.condition.text
                currentTempCelTxt.text = it.day.avgtempC.roundToInt().toString()
                currentTempFarTxt.text = "Feels like ${it.day.avgtempF.roundToInt()}f"
                windSpeedTxt.text = "${it.day.maxwindMph} mph"
                humidityTxt.text = "${it.day.avghumidity} %"
                visibilityTxt.text = "${it.day.avgvisKm} Km"
                snowChanceTxt.text = "${it.day.dailyChanceOfSnow} %"
                rainChanceTxt.text = "${it.day.dailyChanceOfRain} %"
                sunriseTxt.text = it.astro.sunrise
                sunSetTxt.text = it.astro.sunset
                uvTxt.text = it.day.uv.toString()
            }
        }
    }

    //load forecast rec data
    override fun loadDaysForecastWeather(forecastWeather: ForecastWeatherResponse) {
        binding.apply {
        forecastAdapter.setData(forecastWeather.forecast.forecastday)
            forecastRec.apply {
                adapter = forecastAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
        }
    }

    //manage loading progress bar
    override fun showLoading(shown: Boolean) {
        binding.apply {
            if (shown) {
                forecastProgressBar.visibility = View.VISIBLE
                mainLayout.visibility = View.GONE
            } else {
                forecastProgressBar.visibility = View.GONE
                mainLayout.visibility = View.VISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

}