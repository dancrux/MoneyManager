package com.cruxrepublic.moneymanager.ui.profile

import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.data.model.User

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
//        private var getUserDetails = repository.userDetails
     var firstName =repository.userFirstName
    var surname = repository.userSurname
    var email =repository.userEmail
     var phoneNumber =repository.userPhoneNumber
    var age = repository.userAge
     var country = repository.userCountry
    var gender = repository.userSex
     var id = repository.userId
    fun getUserProfile(){
        repository.getUserProfile()

    }
}