package com.khalid.core_data.services.feed

import com.google.gson.Gson
import com.khalid.core.feed.repository.FeedRepository
import com.khalid.core_data.di.NetworkModule
import com.khalid.core_data.services.feed.mappers.FeedMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FeedModule {

    @Provides
    @Singleton
    fun provideFeedApi(@NetworkModule.MainRetrofit retrofit: Retrofit): FeedApi =
        retrofit.create(FeedApi::class.java)

    @Provides
    @Singleton
    fun provideFeedRepository(feedApi: FeedApi, feedMapper: FeedMapper): FeedRepository =
        FeedRepositoryImpl(feedApi, feedMapper)

    @Provides
    @Singleton
    fun provideFeedMapper(gson: Gson): FeedMapper = FeedMapper(gson)
}