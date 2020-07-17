package com.cruxrepublic.moneymanager.data.model

import com.google.firebase.database.Exclude

data class Expense (

    var reasonForExpenses: String? = "",
    var amount: String? = "",
    var time: String? = "",

    @get:Exclude
    var id: String? = ""
){
}