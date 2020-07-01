package com.cruxrepublic.moneymanager.ui.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.data.UserRepository

class IncomeViewModelFactory(private val repository: UserRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T{
        return IncomeViewModel(repository) as T
    }
}