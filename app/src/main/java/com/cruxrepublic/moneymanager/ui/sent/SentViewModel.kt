package com.cruxrepublic.moneymanager.ui.sent

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.data.firebase.notification.NotificationData
import com.cruxrepublic.moneymanager.data.firebase.notification.PushNotificationData
import com.cruxrepublic.moneymanager.data.firebase.notification.RetrofitInstance
import com.cruxrepublic.moneymanager.data.model.Sent
import com.cruxrepublic.moneymanager.ui.auth.AuthListener
import com.google.android.gms.common.api.ApiException
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*


const val TOPIC = "/topics/myTopic"
class SentViewModel(private val repository: UserRepository) : ViewModel() {

    val TAG = "SentFragment"
    var receiversId: String = ""
    var amount: String = ""

    lateinit var authListener: AuthListener
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val sentList: LiveData<List<Sent>> = repository.allSent
    init {
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
    }

    fun sendMoney(view: View) {
        val title = "Money Received"
        val message ="You received $amount"
        if (authListener.validateFields()) {
            authListener.onStarted()
        } else return
        uiScope.launch {
            try {
                val response = repository.sendMoney(receiversId, amount, formatTime())
                response.let {
                    PushNotificationData(NotificationData(
                        title, message), TOPIC
                    ).also {
                        sendNotification(it)
                    }
                    authListener.onSuccess()
                }

            } catch (e: ApiException) {
                authListener.notSuccessful()
                authListener.onFailure(e.message!!)
            }
        }


    }

    fun fetchSent(){
        repository.fetchSent()
    }
    fun deleteSentItem(sent: Sent) = repository.deleteSentItem(sent)

    private fun sendNotification(notification: PushNotificationData) = CoroutineScope(Dispatchers.IO)
        .launch {
        try {
            val response = RetrofitInstance.retrofitApi.postNotifications(notification)
            if(response.isSuccessful){
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            }else{
                Log.e(TAG, response.errorBody().toString())
            }
        }catch (e: Exception){
            Log.e(TAG, e.toString())
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