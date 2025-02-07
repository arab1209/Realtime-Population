package com.example.realtimepopulation.di

import android.content.Context
import com.example.realtimepopulation.data.repository.PopulationRepositoryImpl
import com.example.realtimepopulation.data.repository.SeoulLocationRepositoryImpl
import com.example.realtimepopulation.di.api.SeoulAreaApiService
import com.example.realtimepopulation.domain.repository.PopulationRepository
import com.example.realtimepopulation.domain.repository.SeoulLocationRepository
import com.example.realtimepopulation.domain.usecase.main.GetAreaPopulationDataUseCase
import com.example.realtimepopulation.domain.usecase.main.GetSeoulLocationDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideSeoulLocationRepository(context: Context): SeoulLocationRepository {
        return SeoulLocationRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun providePopulationRepository(seoulAreaApiService: SeoulAreaApiService): PopulationRepository {
        return PopulationRepositoryImpl(seoulAreaApiService)
    }

    @Provides
    @Singleton
    fun provideGetSeoulLocationDataUseCase(repository: SeoulLocationRepository): GetSeoulLocationDataUseCase {
        return GetSeoulLocationDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAreaPopulationDataUseCase(repository: PopulationRepository): GetAreaPopulationDataUseCase {
        return GetAreaPopulationDataUseCase(repository)
    }
}