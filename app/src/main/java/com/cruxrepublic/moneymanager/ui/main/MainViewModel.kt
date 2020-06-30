package com.cruxrepublic.moneymanager.ui.main

import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUserMetadata


class MainViewModel(private val repository: UserRepository
) : ViewModel() {

    lateinit var mainInterface: MainInterface
    private val firebaseAuth = FirebaseAuth.getInstance()


//    var isNew by Delegates.notNull<Boolean>()


    fun logout(){
        repository.logout()
//        view.context.startLoginActivity()
    }

   fun checkIsNewUser(){
       if (repository.checkIsNewUser())
           mainInterface.promptNewUser()
       else mainInterface.promptOldUser("Welcome Back")
   }





}