package com.example.weatherapp.di.module

import android.content.Context
import com.example.weatherapp.data.local.database.SavedImagesDataBase
import com.example.weatherapp.domain.repository.ApiHelper
import com.example.weatherapp.ui.history.HistoryViewModel
import com.example.weatherapp.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class HistoryModule {
    @Provides
    fun provideMainViewModel(
        api: ApiHelper,
        dataBase: SavedImagesDataBase,
        context: Context
    ): HistoryViewModel {
        return HistoryViewModel(api, dataBase, context)
    }
}