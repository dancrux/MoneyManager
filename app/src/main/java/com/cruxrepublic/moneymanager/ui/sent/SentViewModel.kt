package com.cruxrepublic.moneymanager.ui.sent

import android.view.View
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

class SentViewModel(private val repository: UserRepository) : ViewModel() {
    var receiversId: String = ""
    var amount: String = ""

    lateinit var authListener: AuthListener
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var exceptionResult = repository.exceptionResult

    fun sendMoney(view: View) {
        if (authListener.validateFields()) {
            authListener.onStarted()
        } else return
        uiScope.launch {
            try {
                val response = repository.sendMoney(receiversId, amount, formatTime())
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
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}