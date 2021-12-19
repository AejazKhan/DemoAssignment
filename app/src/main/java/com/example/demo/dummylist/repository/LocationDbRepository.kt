package com.example.demo.dummylist.repository

import com.example.demo.db.AppDB
import com.example.demo.dummylist.db.entity.Location
import javax.inject.Inject

/**
 * Created by aejaz.khan.
 * Repository for storing Location data into the DB.
 */
class LocationDbRepository @Inject constructor(val appDB: AppDB) {
    suspend fun insertAll(locationList: List<Location>) {
        appDB.locationDao().insertAll(locationList)
    }
}