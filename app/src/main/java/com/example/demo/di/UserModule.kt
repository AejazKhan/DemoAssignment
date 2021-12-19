package com.example.demo.di

import com.example.demo.db.AppDB
import com.example.demo.dummylist.repository.LocationDbRepository
import com.example.demo.dummylist.repository.UserApiRepository
import com.example.demo.dummylist.repository.UserDbRepository
import com.example.demo.dummylist.repository.UserRepository
import com.example.demo.dummylist.viewmodel.UserViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module()
class UserModule {

    @Provides
    fun provideUserRepository(userApiRepository: UserApiRepository, userDbRepository: UserDbRepository,
                              locationDbRepository: LocationDbRepository)
    = UserRepository(userApiRepository,userDbRepository,locationDbRepository)

    @Provides
    fun provideUserApiRepository(retrofit: Retrofit)  = UserApiRepository(retrofit)

    @Provides
    fun provideUserDbRepository(appDB: AppDB)  = UserDbRepository(appDB)

    @Provides
    fun provideLocationDbRepository(appDB: AppDB)  = LocationDbRepository(appDB)

    @Provides
    fun provideUserViewModelFactory(userRepository: UserRepository)  = UserViewModelFactory(userRepository)
}