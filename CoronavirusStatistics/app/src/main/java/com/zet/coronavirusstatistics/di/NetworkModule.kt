package com.zet.coronavirusstatistics.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zet.coronavirusstatistics.framework.network.api.CoronaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {

    private fun provideBaseUrl(): String = "https://api.covid19api.com/"

    private fun provideOkHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return OkHttpClient.Builder()
            .addNetworkInterceptor(logInterceptor)
            .build()
    }

    private fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun provideRetrofit(
        okHttpClient: OkHttpClient = provideOkHttpClient(),
        baseUrl: String = provideBaseUrl(),
        moshi: Moshi = provideMoshi()
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun provideCoronaApi(): CoronaApi {
        val retrofit = provideRetrofit()
        return retrofit.create(CoronaApi::class.java)
    }
}