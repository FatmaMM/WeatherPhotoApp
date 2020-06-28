package com.example.weatherapp.di.builder
import com.example.weatherapp.di.module.FullScreenProvider
import com.example.weatherapp.di.module.HistoryModule
import com.example.weatherapp.ui.base.MainActivity
import com.example.weatherapp.di.module.MainModule
import com.example.weatherapp.di.module.MainProvider
import com.example.weatherapp.ui.fullimages.FullScreenViewModel
import com.example.weatherapp.ui.fullimages.FullscreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainProvider::class,MainModule::class,HistoryModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FullScreenProvider::class])
    internal abstract fun bindFullActivity(): FullscreenActivity
}