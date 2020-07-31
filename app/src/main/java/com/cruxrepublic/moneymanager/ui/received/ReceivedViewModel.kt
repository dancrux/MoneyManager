package com.cruxrepublic.moneymanager.ui.received

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.data.model.Received

class ReceivedViewModel(private val repository: UserRepository) : ViewModel() {

    val  receivedList: LiveData<List<Received>> = repository.allReceived
    fun fetchReceived(){
        repository.fetchReceived()
    }
    fun deleteReceivedItem(received: Received) = repository.deleteReceivedItem(received)
}