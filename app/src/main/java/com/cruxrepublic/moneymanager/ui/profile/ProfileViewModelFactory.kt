package com.cruxrepublic.moneymanager.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModel

class ProfileViewModelFactory (private val repository: UserRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T{
        return ProfileViewModel(repository) as T
    }
}