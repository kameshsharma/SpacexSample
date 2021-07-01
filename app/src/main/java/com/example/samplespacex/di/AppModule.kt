package com.example.samplespacex.di

import android.app.Application
import androidx.room.Room
import com.airbnb.lottie.BuildConfig
import com.example.samplespacex.data.SpacexRepository
import com.example.samplespacex.data.SpacexHandlerRepository
import com.example.samplespacex.data.source.local.SpacexDatabase
import com.example.samplespacex.data.source.local.SpacexDatabase.Companion.DATABASE_NAME
import com.example.samplespacex.data.source.remote.ApiHandler
import com.example.samplespacex.data.source.remote.ApiHandler.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSpacex(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) BODY else NONE
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteApi(retrofit: Retrofit): ApiHandler =
        retrofit.create(ApiHandler::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): SpacexDatabase =
        Room.databaseBuilder(app, SpacexDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideDefaultSpacexRepository(
        api: ApiHandler,
        db: SpacexDatabase
    ) = SpacexRepository(api, db) as SpacexHandlerRepository

}