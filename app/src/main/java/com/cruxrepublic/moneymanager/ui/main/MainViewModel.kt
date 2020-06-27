package com.cruxrepublic.moneymanager.ui.main

import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository

class MainViewModel(private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    fun logout(){
        repository.logout()
    }


}