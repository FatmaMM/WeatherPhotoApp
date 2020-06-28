package com.example.weatherapp.di.builder
import com.example.weatherapp.di.module.HistoryModule
import com.example.weatherapp.ui.base.MainActivity
import com.example.weatherapp.di.module.MainModule
import com.example.weatherapp.di.module.MainProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainProvider::class,MainModule::class,HistoryModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}