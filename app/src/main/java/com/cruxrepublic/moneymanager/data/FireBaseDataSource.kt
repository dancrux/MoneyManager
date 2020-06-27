package com.cruxrepublic.moneymanager.data

import com.cruxrepublic.moneymanager.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class FireBaseDataSource {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    lateinit var user: User
    var email: String? = null

    var firstName: String = ""
    var surname: String = ""
    var country: String = ""
    var age: String = ""
    var phoneNumber = ""
    var sex = ""
    var errorMessage = ""

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

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!emitter.isDisposed) {
                    if (it.isSuccessful) {
                        user = User(email, firstName, surname, country, age, phoneNumber, sex)

                        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
                            FirebaseDatabase.getInstance().getReference("Users")
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

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}


