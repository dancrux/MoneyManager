package com.cruxrepublic.moneymanager.ui.main

import android.view.View
import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.ui.utils.startLoginActivity

class MainViewModel(private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    fun logout(){
        repository.logout()
//        view.context.startLoginActivity()
    }


}