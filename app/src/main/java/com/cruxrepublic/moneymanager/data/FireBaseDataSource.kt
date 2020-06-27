package com.cruxrepublic.moneymanager.data

import com.cruxrepublic.moneymanager.data.model.LoggedInUser
import com.cruxrepublic.moneymanager.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class FireBaseDataSource() {
//    private val firebaseAuth: FirebaseAuth by lazy {
////        FirebaseAuth.getInstance()
////    }
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    var email : String = ""

    var firstName: String = ""
    var surname: String = ""
    var country: String = ""
    var age: String = ""
    var phoneNumber = ""
    var sex = ""
    var errorMessage = ""
    private lateinit  var user: User

    fun login(email: String, password: String) {
    firebaseAuth.signInWithEmailAndPassword(email,password)
        .addOnCompleteListener {
            if (it.isSuccessful)
                it.isComplete

            else it.exception

    }

    }

//    fun login(email: String, password: String) = Completable.create {
//            emitter ->  firebaseAuth.signInWithEmailAndPassword(email,password)
//        .addOnCompleteListener {
//            if (!emitter.isDisposed){
//                if (it.isSuccessful)
////                it.isComplete
//                    emitter.onComplete()
//                else emitter.onError(it.exception!!)
//            }
//        }
//
//    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                    if (it.isSuccessful) {
                         user = User(firstName, surname, email,country, phoneNumber,sex,age)
                        firebaseAuth.currentUser?.uid?.let {uid->
                            firebaseDatabase.getReference("users")
                                .child(uid).setValue(user).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        it.isComplete
                                    } else
                                       it.exception
                                }

                        }
                    }

                }

            }

//    private fun writeNewUser(userId: String, firstName: String, email: String?
//                             , surname: String, sex: String, country: String, age: String,
//                             phoneNumber: String) {
//        val user = User(firstName, surname, email,country, phoneNumber,sex,age)
//        database.child("users").child(userId).setValue(user)
//    }

//    fun register(email: String, password: String)= Completable.create { emitter ->
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                if (!emitter.isDisposed) {
//                    if (it.isSuccessful) {
//
//                        user = User(
//                            email, firstName, surname, country, age, phoneNumber, sex
//                        )
//                        firebaseAuth.currentUser?.uid?.let {
//                            firebaseDatabase.getReference("Users")
//                                .child(it).setValue(user).addOnCompleteListener {
//                                    if (it.isSuccessful) {
//                                        emitter.onComplete()
//                                    } else
//                                        emitter.onError(it.exception!!)
//                                }
//
//                        }
//                    }
//
//                }
//
//            }
//
//    }
    fun logout() = firebaseAuth.signOut()

    fun currentUser()= firebaseAuth.currentUser
}


