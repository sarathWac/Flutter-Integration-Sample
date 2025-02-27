package com.example.flutter.integration.di

import android.content.Context
import com.example.flutter.integration.data.AndroidFlutterEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
object FlutterEngineModule {

    @Provides
    fun providesFlutterEngine(
        @ApplicationContext context: Context
    ) = AndroidFlutterEngine
        .Builder()
        .setContext(context)
        .setEngineId(AndroidFlutterEngine.DEFAULT_ENGINE_ID)
        .build()


}