package com.cruxrepublic.moneymanager.data

import android.util.Log
import com.cruxrepublic.moneymanager.data.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUserMetadata
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable


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
    var firstName: String? = null
    var surname: String? = null
    var country: String = ""
    var age: String = ""
    var phoneNumber = ""
    var sex = ""
    var userid: String = firebaseAuth.currentUser?.uid.toString()
    var id = userid.filter { it.isDigit() }

    private lateinit  var user: User

//    fun login(email: String, password: String) {
//    firebaseAuth.signInWithEmailAndPassword(email,password)
//        .addOnCompleteListener {task->
//            if (task.isSuccessful)
//                task.isComplete
//
//            else task.exception
//
//    }
//
//    }
fun login(email: String, password: String) = Completable.create { emitter ->
    firebaseAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else emitter.onError(it.exception ?: Exception("An error occurred"))
            }
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

//    fun register(email: String, password: String) {
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//
//                    if (it.isSuccessful) {
//                         user = User(firstName, surname, email,country, phoneNumber,sex,age)
//                        firebaseAuth.currentUser?.uid?.let {uid->
//                            firebaseDatabase.getReference("users")
//                                .child(uid).setValue(user).addOnCompleteListener {task->
//                                    if (task.isSuccessful) {
//                                        task.isComplete
//                                    } else
//                                       task.exception
//                                }
//
//                        }
//                    }
//
//                }
//
//            }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!emitter.isDisposed) {
                    if (it.isSuccessful) {
                        user = User( firstName, surname,id,email, phoneNumber, age, country, sex)

                        firebaseAuth.currentUser?.uid?.let { uid ->
                            firebaseDatabase.getReference("Users")
                                .child(uid).setValue(user).addOnCompleteListener { t ->
                                    if (t.isSuccessful) {
                                        emitter.onComplete()
                                    } else
                                        emitter.onError(t.exception ?: Exception("An error occurred"))
                                }

                        }
                    } else {
                        emitter.onError(it.exception ?: Exception("An error occurred"))
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


   fun checkIsNewUser(): Boolean{
       val metadata: FirebaseUserMetadata? = firebaseAuth.currentUser?.metadata
       return metadata?.creationTimestamp == metadata?.lastSignInTimestamp

   }

    fun currentUser()= firebaseAuth.currentUser
}


