package com.cruxrepublic.moneymanager.data

import androidx.lifecycle.LiveData
import com.cruxrepublic.moneymanager.data.model.Expense
import com.cruxrepublic.moneymanager.data.model.Income
import io.reactivex.Completable
import kotlin.math.exp

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class UserRepository(private val firebase: FireBaseDataSource) {


    var income: String = ""
    var allIncome = firebase.income
    var allExpenses = firebase.expenses

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
      val income = Income(sourceOfIncome = sourceOfIncome, amount = amount, time = time, id = income)
        firebase.addIncome(income)
    }
    fun addExpenses(reasonForExpenses: String, amount: String, time: String) {
        val expense = Expense(reasonForExpenses = reasonForExpenses, amount = amount, time = time, id = income)
        firebase.addExpense(expense)
    }
    fun fetchIncome()  = firebase.fetchIncome()

    fun fetchExpenses() = firebase.fetchExpenses()

//    private fun setLoggedInUser(loggedInUser: Income) {
//        this.user = loggedInUser
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//    }
}