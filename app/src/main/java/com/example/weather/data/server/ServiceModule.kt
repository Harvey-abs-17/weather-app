package com.example.weather.data.server

import com.example.weather.utils.BASE_URL
import com.example.weather.utils.BODY_LOGGING_INTERCEPTOR
import com.example.weather.utils.HEADER_LOGGING_INTERCEPTOR
import com.example.weather.utils.TIME_OUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideBaseurl() = BASE_URL

    @Provides
    @Singleton
    fun provideTimeout() = TIME_OUT

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    @Named(BODY_LOGGING_INTERCEPTOR)
    fun provideBodyHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    @Named(HEADER_LOGGING_INTERCEPTOR)
    fun provideHeaderLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    @Singleton
    fun provideClient(
        time: Long,
        @Named(HEADER_LOGGING_INTERCEPTOR) header: HttpLoggingInterceptor,
        @Named(
            BODY_LOGGING_INTERCEPTOR
        ) body: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(time, TimeUnit.SECONDS)
        .readTimeout(time, TimeUnit.SECONDS)
        .callTimeout(time, TimeUnit.SECONDS)
        .addInterceptor(header)
        .addInterceptor(body)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(base: String, gson: Gson, client: OkHttpClient): ApiServices =
        Retrofit.Builder()
            .baseUrl(base)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
            .build()
            .create(ApiServices::class.java)


}