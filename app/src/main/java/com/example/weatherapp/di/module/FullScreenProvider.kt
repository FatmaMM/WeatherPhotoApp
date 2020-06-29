package com.example.weatherapp.di.module

import android.content.Context
import com.example.weatherapp.data.local.database.SavedImagesDataBase
import com.example.weatherapp.domain.repository.ApiHelper
import com.example.weatherapp.ui.fullScreenimage.FullScreenViewModel
import dagger.Module
import dagger.Provides

@Module
class FullScreenProvider {
    @Provides
    fun provideViewModel(api: ApiHelper, database: SavedImagesDataBase, context: Context): FullScreenViewModel {
        return FullScreenViewModel(api,database,context)
    }
}