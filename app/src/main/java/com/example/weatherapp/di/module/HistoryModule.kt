package com.example.weatherapp.di.module

import com.example.weatherapp.ui.history.HistoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HistoryModule {
    @ContributesAndroidInjector(modules = [HistoryProvider::class])
    abstract fun provideProfitFragment(): HistoryFragment
}
