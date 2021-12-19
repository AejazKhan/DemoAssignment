package com.example.demo.dummylist.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.demo.db.base.BaseDao
import com.example.demo.dummylist.db.entity.User
import com.example.demo.dummylist.db.entity.UserWithLocation
import kotlinx.coroutines.flow.Flow

/**
 * Created by aejaz.khan.
 */
@Dao
interface UserDao : BaseDao<User>{
    @Transaction
    @Query("select * from User")
    fun getAllUsers(): Flow<List<UserWithLocation>>
}