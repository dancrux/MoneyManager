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
class FireBaseDataSource {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    lateinit var user: User
    var email : String? = null

    var firstName: String = ""
    var surname: String = ""
    var country: String = ""
    var age: String = ""
    var phoneNumber = ""
    var sex = ""
    var errorMessage = ""

    fun login(email: String, password: String) = Completable.create {
        emitter ->  firebaseAuth.signInWithEmailAndPassword(email,password)
        .addOnCompleteListener {
        if (!emitter.isDisposed){
            if (it.isSuccessful)
                emitter.onComplete()
            else emitter.onError(it.exception!!)
        }
    }

    }

    fun register(email: String, password: String)= Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!emitter.isDisposed) {
                    if (it.isSuccessful) {

                        user = User(
                            email, firstName, surname, country, age, phoneNumber, sex
                        )

                        FirebaseAuth.getInstance().currentUser?.uid?.let {
                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(it).setValue(user).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        emitter.onComplete()
                                    } else
                                        emitter.onError(it.exception!!)
                                }

                        }
                    }

                }

            }

    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser()= firebaseAuth.currentUser
}


