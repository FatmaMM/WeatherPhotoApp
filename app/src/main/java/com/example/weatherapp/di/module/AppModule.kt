package com.example.weatherapp.di.module
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.local.database.SavedImagesDataBase
import com.example.weatherapp.data.network.ApiHelperImplementation
import com.example.weatherapp.domain.repository.ApiHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    lateinit var application: Application
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        this.application = application
        return application
    }

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: ApiHelperImplementation): ApiHelper = appApiHelper

    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): SavedImagesDataBase {
        return Room.databaseBuilder(context, SavedImagesDataBase::class.java, "genredb").build()
    }
}