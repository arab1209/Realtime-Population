package com.example.realtimepopulation.di

import android.content.Context
import coil.ImageLoader
import com.example.realtimepopulation.BuildConfig
import com.example.realtimepopulation.di.api.SeoulAirQualityApiService
import com.example.realtimepopulation.di.api.SeoulAreaApiService
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoilClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @RetrofitClient
    fun provideRetrofitOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    @CoilClient
    fun provideCoilOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .addHeader("Referer", "https://data.seoul.go.kr/")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@RetrofitClient client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://openapi.seoul.go.kr:8088/${BuildConfig.REALTIME_API_KEY}/")
            .client(client)
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder().exceptionOnUnreadXml(false).build()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideSeoulAreaApiService(retrofit: Retrofit): SeoulAreaApiService {
        return retrofit.create(SeoulAreaApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSeoulAirQualityApiService(retrofit: Retrofit): SeoulAirQualityApiService {
        return retrofit.create(SeoulAirQualityApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        @CoilClient okHttpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(okHttpClient)
            .crossfade(true)
            .build()
    }
}