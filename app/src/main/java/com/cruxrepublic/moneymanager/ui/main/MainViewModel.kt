package com.cruxrepublic.moneymanager.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates


class MainViewModel(private val repository: UserRepository
) : ViewModel() {

    lateinit var mainInterface: MainInterface
    private val firebaseAuth = FirebaseAuth.getInstance()


//    var isNew by Delegates.notNull<Boolean>()


    fun logout(){
        repository.logout()
//        view.context.startLoginActivity()
    }

   fun firstLogin(){
        var completeListener =
           OnCompleteListener<AuthResult> { task ->
               if (task.isSuccessful) {
                 val isNew = task.result!!.additionalUserInfo!!.isNewUser
                   if(isNew){
                       mainInterface.promptNewUser()
                   }
//                Log.d("MyTAG", "onComplete: " + if (isNew) "new user" else "old user")
               }
           }
   }





}