package uly.practice.fetchrewards2024.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uly.practice.fetchrewards2024.data.AppConstants
import uly.practice.fetchrewards2024.data.api.ApiService
import uly.practice.fetchrewards2024.data.datasource.FetchRewardsDataImplementation
import uly.practice.fetchrewards2024.data.datasource.FetchRewardsDataSource
import uly.practice.fetchrewards2024.data.repository.FetchRewardsRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetroFit() : Retrofit{

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor((httpLoggingInterceptor))
        }
        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return  Retrofit.Builder()
            .baseUrl(AppConstants.APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesFetchRewardsDataSource(apiService: ApiService) : FetchRewardsDataSource{
        return FetchRewardsDataImplementation(apiService)
    }

    @Provides
    @Singleton
    fun providesFetchRewardsRepository(fetchRewardsDataSource: FetchRewardsDataSource): FetchRewardsRepository{
        return FetchRewardsRepository(fetchRewardsDataSource)
    }


}