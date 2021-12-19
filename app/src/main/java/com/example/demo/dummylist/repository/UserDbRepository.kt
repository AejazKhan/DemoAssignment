package com.example.demo.dummylist.repository

import com.example.demo.db.AppDB
import com.example.demo.dummylist.db.entity.User
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject


/**
 * Created by aejaz.khan.
 * Repository for storing user data into the DB.
 */
class UserDbRepository @Inject constructor(val appDB: AppDB) {
    suspend fun getAllUsers() = appDB.userDao().getAllUsers().distinctUntilChanged()

    suspend fun insertAll(userList: List<User>) = appDB.userDao().insertAll(userList)
}