package org.example.app.di

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * module to provide android preference settings for shared multiplatform factory
 */
@InstallIn(SingletonComponent::class)
@Module
object SettingsModule {
    @Provides
    @Singleton
    fun provideSettings(@ApplicationContext context: Context): Settings = AndroidSettings(
        delegate = context.getSharedPreferences("app", Context.MODE_PRIVATE)
    )
}
