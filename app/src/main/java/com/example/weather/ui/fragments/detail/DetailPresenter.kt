package com.example.weather.ui.fragments.detail

import android.util.Log
import com.example.weather.data.repository.DetailRepository
import com.example.weather.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val repository: DetailRepository,
    private val view: DetailContract.View
) : BasePresenterImpl(), DetailContract.Presenter {

    //get current weather
    override fun getCurrentWeatherPresenter(location: String) {
        view.showLoading(true)
        disposable = repository.getCurrentWeather(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showLoading(false)
                view.loadCurrentWeather(it)
            }, {
                Log.e("error", it.toString())
            })
    }

    //get forecast weather
    override fun getForecastWeatherPresenter(location: String, days: Int) {
        disposable = repository.getForecastWeatherRepository(location = location, days = days)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.loadForecastRec(it)
                view.loadWeatherChart(it)
            }, {
                Log.e("error", it.toString())

            })
    }


}