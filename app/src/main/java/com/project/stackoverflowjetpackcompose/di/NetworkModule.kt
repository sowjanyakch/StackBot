package com.project.stackoverflowjetpackcompose.di


import com.example.stackoverflowjetpackcompose.BuildConfig
import com.project.stackoverflowjetpackcompose.network.StackOverflowAPI
import com.project.stackoverflowjetpackcompose.repository.StackOverflowRepository
import com.project.stackoverflowjetpackcompose.repository.StackOverflowRepositoryImplementation
import com.project.stackoverflowjetpackcompose.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val okHttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(okHttpLoggingInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideStackOverflowAPI(retrofit: Retrofit): StackOverflowAPI {
        return retrofit.create(StackOverflowAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideStackOverflowRepository(api:StackOverflowAPI): StackOverflowRepository{
        return StackOverflowRepositoryImplementation(api)
    }

}




