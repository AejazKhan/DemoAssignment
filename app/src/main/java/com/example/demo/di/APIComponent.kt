package com.example.demo.di

import dagger.Component

/**
 * Created by aejaz.khan on 14-10-2021.
 */
@Component( modules = [ApplicationModule::class,ApiModule::class])
interface APIComponent {
//    fun inject(userRepo : UserViewModel)
}
