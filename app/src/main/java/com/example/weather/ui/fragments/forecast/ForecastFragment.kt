package com.example.weather.ui.fragments.forecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private lateinit var binding: FragmentForecastBinding
    private val forecastArgs: ForecastFragmentArgs by navArgs()

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
        presenter.getDaysForecastWeatherPresenter(forecastArgs.location, 3)
        bindDetailForecastViews()

    }

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
            }
        }
    }


    override fun loadDaysForecastWeather(forecastWeather: ForecastWeatherResponse) {
        binding.apply {
        forecastAdapter.setData(forecastWeather.forecast.forecastday)
            forecastRec.apply {
                adapter = forecastAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
        }
    }

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