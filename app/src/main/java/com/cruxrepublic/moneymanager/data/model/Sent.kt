package com.cruxrepublic.moneymanager.data.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.Exclude

data class Sent (
    @get:Exclude
    var id: String? = "",
    val sendersId: String =  FirebaseAuth.getInstance().currentUser?.uid.toString().filter { it.isUpperCase() },
    var receiversId: String? = "",
    var amount: String? = "",
    var time: String? = ""
)