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

   private fun checkIsNewUser(): Boolean{
       if (!repository.checkIsNewUser()) {
           prefs.saveFirstTimeLoginBoolean(sharedPref, false)
           return false
       } else  prefs.saveFirstTimeLoginBoolean(sharedPref, true)
       return true
   }

    fun showNewUserDialog(){
        if (checkIsNewUser()){
            prefs.firstTimeLoginBoolean(sharedPref)
            mainInterface.promptNewUser()
        }else mainInterface.promptOldUser("Welcome Back")
    }
}