package com.example.weather.ui.fragments.forecast

import android.util.Log
import com.example.weather.data.repository.ForecastRepository
import com.example.weather.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ForecastPresenter @Inject constructor(
    private val repository: ForecastRepository,
    private val view: ForecastContract.View
) : BasePresenterImpl(), ForecastContract.Presenter {

    override fun getDaysForecastWeatherPresenter(location: String, days: Int) {
        view.showLoading(true)
        disposable = repository.getDaysForecastWeatherRepository(location, days)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showLoading(false)
                view.loadDaysForecastWeather(it)
            }, {
                Log.e("error", it.toString())
            })
    }

}