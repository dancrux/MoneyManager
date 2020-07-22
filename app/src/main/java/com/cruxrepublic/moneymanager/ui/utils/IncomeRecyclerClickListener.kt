package com.cruxrepublic.moneymanager.ui.utils

import android.view.View
import com.cruxrepublic.moneymanager.data.model.Income

interface IncomeRecyclerClickListener {
    fun onItemClicked(view: View, income: Income)
}