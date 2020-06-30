package com.cruxrepublic.moneymanager.data

import com.cruxrepublic.moneymanager.data.model.LoggedInUser
import io.reactivex.Completable

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class UserRepository(private val firebase: FireBaseDataSource) {

    // in-memory cache of the loggedInUser object
//    var user: LoggedInUser? = null
//        private set

//    val isLoggedIn: Boolean
//        get() = user != null
//
//    init {
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//        user = null
//    }

     fun login(email: String, password: String) = firebase.login(email, password)


   fun register(
        email: String,
        password: String,
        firstName: String,
        surname: String,
        phoneNumber: String,
        age: String,
        country: String,
        sex: String): Completable{
       firebase.firstName =firstName
       firebase.surname = surname
       firebase.phoneNumber = phoneNumber
       firebase.age = age
       firebase.country = country
       firebase.sex = sex

          return firebase.register(email, password)
    }


    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

    fun checkIsNewUser() = firebase.checkIsNewUser()

//    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
//        this.user = loggedInUser
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//    }
}