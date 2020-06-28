package com.example.weatherapp.di.module

import android.content.Context
import com.example.weatherapp.data.local.database.SavedImagesDataBase
import com.example.weatherapp.domain.repository.ApiHelper
import com.example.weatherapp.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainProvider {
    @Provides
    fun provideMainViewModel(api: ApiHelper, database: SavedImagesDataBase, context: Context): MainViewModel {
        return MainViewModel(api,database,context)
    }
}