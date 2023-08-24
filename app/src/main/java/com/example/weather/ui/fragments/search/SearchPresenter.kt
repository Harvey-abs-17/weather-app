package com.example.weather.ui.fragments.search

import com.example.weather.data.repository.SearchRepository
import com.example.weather.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val repository: SearchRepository,
    private val view: SearchContract.View
) : BasePresenterImpl(), SearchContract.Presenter {

    override fun searchLocationPresenter(location: String) {
        view.showLoading()
        disposable = repository.searchLocationRepository(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if(it.isNotEmpty()){
                    view.loadLocations(it)
                }else{
                    view.showEmpty()
                }
            }
        view.hideLoading()
    }
}