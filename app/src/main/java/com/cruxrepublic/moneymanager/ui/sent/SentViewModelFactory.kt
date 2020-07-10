package com.cruxrepublic.moneymanager.ui.sent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModel

class SentViewModelFactory(private val repository: UserRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T{
        return SentViewModel(repository) as T
    }
}