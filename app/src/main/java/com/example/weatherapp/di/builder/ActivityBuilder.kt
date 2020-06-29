package com.example.weatherapp.di.builder
import com.example.weatherapp.di.module.FullScreenProvider
import com.example.weatherapp.di.module.HistoryProvider
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.di.module.MainProvider
import com.example.weatherapp.di.module.MainModule
import com.example.weatherapp.ui.fullScreenimage.FullscreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class,MainProvider::class,HistoryProvider::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FullScreenProvider::class])
    internal abstract fun bindFullActivity(): FullscreenActivity
}