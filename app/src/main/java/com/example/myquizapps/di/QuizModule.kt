package com.example.myquizapps.di
import com.example.myquizapps.retrofit.ApiService
import com.example.myquizapps.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class QuizModule {
    @Singleton
    @Provides
    fun providerRetrofitBuilder(
    ) : Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

   @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit.Builder): ApiService {
    return retrofit.build().create(ApiService::class.java)
    }
}