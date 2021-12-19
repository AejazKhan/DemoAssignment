package com.example.demo.di

import com.example.demo.dummylist.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by aejaz.khan on 14-10-2021.
 */
@Singleton
@Component(modules = [UserModule::class,ApiModule::class,ApplicationModule::class,DBModule::class])
interface UserComponent {
//    fun repository() : UserRepository
//
//    fun apiRepository() : UserApiRepository
//
//    fun dbRepository() : UserDbRepository

    fun inject(mainActivity: MainActivity)
}