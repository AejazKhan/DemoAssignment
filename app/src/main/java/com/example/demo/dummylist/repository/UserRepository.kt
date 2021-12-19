package com.example.demo.dummylist.repository

import com.example.demo.dummylist.db.entity.Location
import com.example.demo.dummylist.db.entity.User
import com.example.demo.dummylist.model.RandomUsers
import javax.inject.Inject

/**
 * Created by aejaz.khan.
 * Repository for user.
 */
class UserRepository @Inject constructor(val userApiRepository: UserApiRepository,val userDbRepository: UserDbRepository, val locationDbRepository: LocationDbRepository) {

    suspend fun getAllUsers() = userDbRepository.getAllUsers()

    suspend fun getRandomUsers(result: Int) {
        val randomUsers = userApiRepository.fetchRandomUsers(result)
        if(randomUsers != null){
            storeUserData(randomUsers)
        }
    }
    private suspend fun storeUserData(randomUsers: RandomUsers) {
        if (randomUsers.results == null) {
            return
        }
        val userList = arrayListOf<User>()
        val locationList = arrayListOf<Location>()
        for (result in randomUsers.results) {
            if (result?.login?.uuid != null) {
                userList.add(
                    User(
                        result.login.uuid,
                        result.name?.title,
                        result.name?.first,
                        result.name?.last,
                        result.email,
                        result.gender,
                        result.dob?.date,
                        result.dob?.age,
                        result.phone,
                        result.cell,
                        result.picture?.large,
                        result.picture?.medium,
                        result.picture?.thumbnail,
                        result.nat,
                        System.currentTimeMillis()
                    )
                )
                locationList.add(
                    Location(
                        uuid = result.login.uuid,
                        streetName = result.location?.street?.name,
                        streetNumber = result.location?.street?.number,
                        city = result.location?.city,
                        state = result.location?.state,
                        country = result.location?.country,
                        postCode = result.location?.postcode
                    )
                )
            }
            insertRandomUsers(userList,locationList)
        }
    }
    private suspend fun insertRandomUsers(userList: List<User>, locationList: List<Location>){
        locationDbRepository.insertAll(locationList)
        userDbRepository.insertAll(userList)
    }
}