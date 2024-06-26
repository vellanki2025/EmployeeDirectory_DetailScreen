package com.organization.directory.core.di

import android.content.Context
import com.organization.directory.core.common.NetworkConnectionInterceptor
import com.organization.directory.data.api.EmployeeApi
import com.organization.directory.data.util.PhoneNumberUtilsWrapper
import com.organization.directory.data.util.PhoneNumberUtilsWrapperImpl
import com.organization.directory.utils.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger module providing app-level dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the [EmployeeApi] using Retrofit and OkHttpClient.
     *
     * @param appContext The [Context] of the application.
     * @return An instance of [EmployeeApi] created using Retrofit and OkHttpClient.
     */
    @Singleton
    @Provides
    fun provideEmployeeApi(@ApplicationContext appContext: Context): EmployeeApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(NetworkConnectionInterceptor(appContext))
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeApi::class.java)
    }

    @Provides
    @Singleton
    fun providePhoneNumberUtilsWrapper(): PhoneNumberUtilsWrapper {
        return PhoneNumberUtilsWrapperImpl()
    }
}