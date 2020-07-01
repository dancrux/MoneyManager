package com.cruxrepublic.moneymanager.ui.income

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.ui.auth.AuthListener
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class IncomeViewModel(private val repository: UserRepository) : ViewModel() {
    var sourceOfIncome: String = ""
    var amount: String = ""

    lateinit var authListener: AuthListener
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    fun addIncome(view: View) {
        if (authListener.validateFields()) {
            authListener.onStarted()
        } else return
        uiScope.launch {
            try {
                val response = repository.addIncome(sourceOfIncome, amount, formatTime())
                response.let {
                    authListener.onSuccess()
                }

            } catch (e: ApiException) {
                authListener.notSuccessful()
                authListener.onFailure(e.message!!)
            }

        }
    }
    private fun formatTime(): String {
        val dateFormat = DateFormat.getDateTimeInstance()
        val date = Date()
        val dateTime = dateFormat.format(date)
        return dateTime.toString()
    }
}