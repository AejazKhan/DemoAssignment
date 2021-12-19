package com.example.demo.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by aejaz.khan on 14-10-2021.
 */
@Module
class ApplicationModule(private val context : Context) {

    @Provides
    fun provideContext() = context
}