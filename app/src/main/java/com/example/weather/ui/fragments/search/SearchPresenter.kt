package com.example.weather.ui.fragments.search

import android.util.Log
import com.example.weather.data.model.SearchWeatherResponse
import com.example.weather.data.model.SearchWeatherResponse.SearchWeatherResponseItem
import com.example.weather.data.repository.SearchRepository
import com.example.weather.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val repository: SearchRepository,
    private val view: SearchContract.View
) : BasePresenterImpl(), SearchContract.Presenter {

    override fun searchLocationPresenter(location: String) {
        view.showLoading(true)
        disposable = repository.searchLocationRepository(location)
            .switchMap{ t->
                Observable.just(t)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()) {
                    view.loadLocations(it)
                } else {
                    view.showEmpty()
                }
                view.showLoading(false)
            }, {
                Log.e("error", it.toString())
            })
    }
}