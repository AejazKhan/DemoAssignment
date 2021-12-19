package com.example.demo.di

import android.content.Context
import com.example.demo.db.AppDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by aejaz.khan on 14-10-2021.
 */
@Module()
class DBModule {

    @Singleton
    @Provides
    fun provideDB(context : Context) : AppDB {
        AppDB.initDatabase(context)
        return AppDB.getAppDB()
    }
}