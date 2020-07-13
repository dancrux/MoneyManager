package com.cruxrepublic.moneymanager.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.data.preference.Preferences
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUserMetadata

const val sharedPref = "IsFirstLogin"
class MainViewModel(private val repository: UserRepository, private val prefs: Preferences
) : ViewModel() {

    lateinit var mainInterface: MainInterface
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun logout(){
        repository.logout()
//        view.context.startLoginActivity()
    }
    fun showNewUserDialog(){
        val isNewUser = prefs.firstTimeLoginBoolean(sharedPref)
        if (isNewUser){
            mainInterface.promptNewUser()
            return
        }else mainInterface.promptOldUser("Welcome Back")
    }
}