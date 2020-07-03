package com.cruxrepublic.moneymanager.ui.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModel

class ExpenseViewModelFactory(private val repository: UserRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T{
        return ExpenseViewModel(repository) as T
    }
}