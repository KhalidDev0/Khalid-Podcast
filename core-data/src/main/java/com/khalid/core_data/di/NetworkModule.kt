package com.khalid.core_data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khalid.core_data.utils.NetworkConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Qualifier
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Qualifier
    annotation class MainBaseUrl

    @Qualifier
    annotation class SearchBaseUrl

    @Qualifier
    annotation class MainRetrofit

    @Qualifier
    annotation class SearchRetrofit

    @Provides
    @MainBaseUrl
    fun provideMainUrl() = NetworkConstant.MAIN_BASE_URL

    @Provides
    @SearchBaseUrl
    fun provideSearchUrl() = NetworkConstant.SEARCH_BASE_URL

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    @MainRetrofit
    fun provideMainRetrofit(
        @MainBaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = buildRetrofit(baseUrl, okHttpClient, gson)

    @Provides
    @Singleton
    @SearchRetrofit
    fun provideSearchRetrofit(
        @SearchBaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = buildRetrofit(baseUrl, okHttpClient, gson)

    private fun buildRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}