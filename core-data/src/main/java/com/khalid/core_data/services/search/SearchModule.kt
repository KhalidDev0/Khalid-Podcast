package com.khalid.core_data.services.search

import com.khalid.core.search.reposirtory.SearchRepository
import com.khalid.core_data.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchApi(@NetworkModule.SearchRetrofit retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)

    @Provides
    @Singleton
    fun provideFeedRepository(searchApi: SearchApi): SearchRepository =
        SearchRepositoryImpl(searchApi)
}