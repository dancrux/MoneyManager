package com.cruxrepublic.moneymanager.ui.utils


import android.view.View
import com.cruxrepublic.moneymanager.data.model.Sent

interface SentRecyclerClickListener {
    fun onItemClicked(view: View, sent: Sent)
}