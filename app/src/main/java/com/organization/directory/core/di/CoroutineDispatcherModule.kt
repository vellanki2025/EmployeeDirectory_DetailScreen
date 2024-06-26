package com.organization.directory.core.di

import com.organization.directory.core.common.CoroutineDispatchProvider
import com.organization.directory.core.common.DefaultDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for providing a custom [CoroutineDispatchProvider] implementation.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineDispatcherModule {

    /**
     * Binds the [DefaultDispatcherProvider] to the [CoroutineDispatchProvider] interface.
     *
     * @param defaultDispatcherProvider The implementation of [CoroutineDispatchProvider] to be bound.
     * @return The bound [CoroutineDispatchProvider] instance.
     */
    @Binds
    @Singleton
    abstract fun bindCoroutineDispatchProvider(defaultDispatcherProvider: DefaultDispatcherProvider): CoroutineDispatchProvider
}