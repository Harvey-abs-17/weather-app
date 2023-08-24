package com.example.weather.utils

import androidx.fragment.app.Fragment
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
    fun provideView(fragment :Fragment) :SearchContract.View = fragment as SearchContract.View
}