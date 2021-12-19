package com.example.demo.dummylist.viewmodel

import androidx.lifecycle.*
import com.example.demo.dummylist.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by aejaz.khan.
 * ViewModel for user.
 */
class UserViewModel(val userRepository: UserRepository) : ViewModel() {

    private val pageNumber = MutableLiveData<Int>(0)

    fun setPageNumber(pageCount: Int){
        pageNumber.value = pageCount
    }

    val userList  = pageNumber.switchMap {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getRandomUsers(10)
        }
        liveData (Dispatchers.IO) {
            emitSource(userRepository.getAllUsers().asLiveData())
        }
    }

//    val userList: LiveData<List<UserWithLocation>> = liveData {
//        emitSource(userRepository.getAllUsers().asLiveData())
//        viewModelScope.launch(Dispatchers.IO) {
//            userRepository.getRandomUsers(10)
//        }
//    }


}