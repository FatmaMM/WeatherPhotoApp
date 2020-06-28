package com.example.weatherapp.di.module

import com.example.weatherapp.domain.repository.ApiHelper
import com.example.weatherapp.ui.history.HistoryFragment
import com.example.weatherapp.ui.main.MainFragment
import com.example.weatherapp.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @ContributesAndroidInjector(modules = [MainProvider::class])
    abstract fun provideProfitFragment(): MainFragment
}
