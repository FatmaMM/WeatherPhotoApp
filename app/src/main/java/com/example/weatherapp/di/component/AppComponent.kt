package com.example.weatherapp.di.component

import android.app.Application
import com.example.weatherapp.WeatherPhotoApp
import com.example.weatherapp.di.builder.ActivityBuilder
import com.example.weatherapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {
    fun inject(app: WeatherPhotoApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}