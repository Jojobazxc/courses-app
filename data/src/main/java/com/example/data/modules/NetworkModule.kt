package com.example.data.modules

import android.content.Context
import com.example.data.network.api.CoursesApi
import com.example.data.network.interceptors.MockCoursesInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String = "http://api.example.com"

    @Provides
    fun okHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(MockCoursesInterceptor(context))
            .build()
    }

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl).build()
    }

    @Provides
    fun provideCoursesApi(
        retrofit: Retrofit
    ): CoursesApi = retrofit.create(CoursesApi::class.java)


}