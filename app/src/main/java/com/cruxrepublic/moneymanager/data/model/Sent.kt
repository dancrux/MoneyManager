package com.cruxrepublic.moneymanager.data.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.Exclude

data class Sent (
    @get:Exclude
    val userId: String = FirebaseAuth.getInstance().currentUser?.uid.toString(),
    val sendersUid: String =  userId.filter { it.isUpperCase() },
    var receiversId: String? = "",
    var amount: String? = "",
    var time: String? = ""
)