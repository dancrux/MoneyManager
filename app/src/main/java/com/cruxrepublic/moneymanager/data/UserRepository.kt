package com.cruxrepublic.moneymanager.data

import androidx.lifecycle.LiveData
import com.cruxrepublic.moneymanager.data.model.Expense
import com.cruxrepublic.moneymanager.data.model.Income
import com.cruxrepublic.moneymanager.data.model.Sent
import com.cruxrepublic.moneymanager.data.model.User
import io.reactivex.Completable
import kotlin.math.exp

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class UserRepository(private val firebase: FireBaseDataSource) {


    var income: String = ""
    var exceptionResult = firebase.result
    var allIncome = firebase.income
    var allExpenses = firebase.expenses
    var allReceived = firebase.received
    var allSent = firebase.sent
//    var userDetails = firebase.userDetails

     var userEmail = firebase.userEmail
    var userFirstName = firebase.userFirstName
    var userSurname = firebase.userSurname
    var userCountry = firebase.userCountry
    var userAge = firebase.userAge
     var userPhoneNumber = firebase.userPhoneNumber
      var userSex = firebase.userSex
      var userId = firebase.userId



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

    fun addIncome(sourceOfIncome: String, amount: String, time: String) {
      val income = Income(sourceOfIncome, amount, time)
        firebase.addIncome(income)
    }
    fun addExpenses(reasonForExpenses: String, amount: String, time: String) {
        val expense = Expense(reasonForExpenses, amount, time)
        firebase.addExpense(expense)
    }
    fun fetchIncome()  = firebase.fetchIncome()

    fun fetchExpenses() = firebase.fetchExpenses()

    fun fetchReceived() = firebase.getReceived()

    fun fetchSent() = firebase.getSentRecord()

    fun sendMoney(receiversId: String, amount: String, time: String){
        val send = Sent(receiversId = receiversId, amount = amount, time = time)
        firebase.sendMoney(send)
    }
    fun getUserProfile() = firebase.getProfile()


//    private fun setLoggedInUser(loggedInUser: Income) {
//        this.user = loggedInUser
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//    }
}