package com.cruxrepublic.moneymanager.ui.utils

import android.view.View
import com.cruxrepublic.moneymanager.data.model.Expense
import com.google.android.gms.common.data.DataHolder

interface ExpenseRecyclerClickListener {
    fun onItemClicked(view: View, expense: Expense)
}