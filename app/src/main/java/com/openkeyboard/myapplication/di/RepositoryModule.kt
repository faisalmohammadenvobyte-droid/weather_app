package com.openkeyboard.myapplication.di

import com.openkeyboard.myapplication.data.remote.ApiService
import com.openkeyboard.myapplication.data.repository.WeatherRepositoryImpl
import com.openkeyboard.myapplication.domain.repository.WeatherRepository
import com.openkeyboard.myapplication.domain.usecase.GetWeatherDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(api: ApiService): WeatherRepository =
        WeatherRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetUsersUseCase(weatherRepository: WeatherRepository): GetWeatherDataUseCase =
        GetWeatherDataUseCase(weatherRepository)
}