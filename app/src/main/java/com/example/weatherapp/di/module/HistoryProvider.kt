package com.example.weatherapp.di.module

import com.example.weatherapp.ui.history.HistoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HistoryProvider {
    @ContributesAndroidInjector(modules = [HistoryModule::class])
    abstract fun provideProfitFragment(): HistoryFragment
}
