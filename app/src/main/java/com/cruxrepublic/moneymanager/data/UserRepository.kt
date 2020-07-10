package com.cruxrepublic.moneymanager.data

import androidx.lifecycle.LiveData
import com.cruxrepublic.moneymanager.data.model.Expense
import com.cruxrepublic.moneymanager.data.model.Income
import com.cruxrepublic.moneymanager.data.model.Sent
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

    fun sendMoney(receiversId: String, amount: String, time: String){
        val send = Sent(receiversId = receiversId, amount = amount, time = time)
        firebase.sendMoney(send)
    }

//    private fun setLoggedInUser(loggedInUser: Income) {
//        this.user = loggedInUser
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//    }
}