package com.example.weatherapp

import android.app.Activity
import android.app.Application
import androidx.multidex.MultiDexApplication
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.example.weatherapp.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class WeatherPhotoApp : Application(), HasActivityInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
        AndroidNetworking.initialize(this)
        val debug = true
        if (BuildConfig.DEBUG && debug)
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
    }
}