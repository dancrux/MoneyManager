package com.cruxrepublic.moneymanager.ui.profile

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

     var firstName = repository.firstName
    var surname = repository.surname
    var email =repository.profileEmail
     var phoneNumber =repository.profilePhoneNumber
    var age = repository.age
     var country = repository.country
    var gender = repository.gender
     var id = repository.profileId

    fun getUserProfile(){
        repository.getUserProfile()

    }
}