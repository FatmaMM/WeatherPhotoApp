package com.example.weatherapp.di.module

import com.example.weatherapp.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainProvider {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideProfitFragment(): MainFragment
}
