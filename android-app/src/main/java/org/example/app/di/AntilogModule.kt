package org.example.app.di

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.DebugAntilog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * module to provide Antilog for multiplatform factory
 */
@InstallIn(SingletonComponent::class)
@Module
object AntilogModule {
    @Provides
    @Singleton
    fun provideAntilog(): Antilog = DebugAntilog()
}