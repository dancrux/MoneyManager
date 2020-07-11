package com.cruxrepublic.moneymanager.ui.received

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.ui.auth.AuthViewModel

class ReceivedViewModelFactory(private val repository: UserRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReceivedViewModel(repository) as T
    }
}