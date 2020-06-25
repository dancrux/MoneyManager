package com.cruxrepublic.moneymanager.data

import com.cruxrepublic.moneymanager.data.model.LoggedInUser

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


//    fun login(username: String, password: String): Result<LoggedInUser> {
//        // handle login
//        val result = dataSource.login(username, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()


//    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
//        this.user = loggedInUser
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//    }
}