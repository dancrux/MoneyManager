package com.cruxrepublic.moneymanager.ui.received

import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository

class ReceivedViewModel(private val repository: UserRepository) : ViewModel() {
     var receivedList = repository.allReceived
    fun fetchReceived(){
        repository.fetchReceived()
    }
}