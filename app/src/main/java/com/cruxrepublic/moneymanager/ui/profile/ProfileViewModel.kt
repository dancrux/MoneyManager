package com.cruxrepublic.moneymanager.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.data.model.User
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
//        private var getUserDetails = repository.userDetails
    init {
        viewModelScope.launch {
            firstName
            surname
            email
            phoneNumber
            age
            country
            gender
            id
        }
    }


    val firstName: LiveData<Any> = repository.firstName
    val surname: LiveData<Any> = repository.surname
    val email: LiveData<Any> = repository.profileEmail
    val phoneNumber: LiveData<Any> = repository.profilePhoneNumber
    val country: LiveData<Any> = repository.country
    val age: LiveData<Any> = repository.age
    val gender: LiveData<Any>  =  repository.gender
    val id: LiveData<Any> = repository.profileId


    fun getUserProfile(){
        repository.getUserProfile()

    }
}