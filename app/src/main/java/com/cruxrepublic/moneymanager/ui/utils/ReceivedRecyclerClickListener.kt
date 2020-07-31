package com.cruxrepublic.moneymanager.ui.utils

import android.view.View
import com.cruxrepublic.moneymanager.data.model.Received

interface ReceivedRecyclerClickListener {
    fun onItemClicked(view: View, received: Received )
}