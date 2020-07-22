package com.cruxrepublic.moneymanager.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.data.preference.Preferences
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUserMetadata
import kotlinx.coroutines.launch

const val sharedPref = "IsFirstLogin"
class MainViewModel(private val repository: UserRepository, private val prefs: Preferences
) : ViewModel() {

    lateinit var mainInterface: MainInterface

    val id: LiveData<Any>? = repository.profileId
    val firstName: LiveData<Any>? = repository.firstName
    init {
        repository.getUserProfile()
    }

    fun logout(){
        repository.logout()
//        view.context.startLoginActivity()
    }
    fun showNewUserDialog(){
        val isNewUser = prefs.firstTimeLoginBoolean(sharedPref)
        if (isNewUser){
            prefs.saveFirstTimeLoginBoolean(sharedPref, false)
            mainInterface.promptNewUser()
            return
        }
    }
    fun showNavHeaderText(){
        repository.getUserProfile()
    }
}