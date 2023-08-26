package com.example.weather.utils

import androidx.fragment.app.Fragment
import com.example.weather.ui.fragments.detail.DetailContract
import com.example.weather.ui.fragments.forecast.ForecastContract
import com.example.weather.ui.fragments.search.SearchContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object ContractViewModuleFragment {

    @Provides
    @FragmentScoped
    fun provideSearchView(fragment :Fragment) :SearchContract.View = fragment as SearchContract.View

    @Provides
    @FragmentScoped
    fun provideDetailView(fragment :Fragment) :DetailContract.View = fragment as DetailContract.View

    @Provides
    @FragmentScoped
    fun provideForecastView(fragment :Fragment) :ForecastContract.View = fragment as ForecastContract.View
}