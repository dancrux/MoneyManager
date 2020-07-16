package com.cruxrepublic.moneymanager.data.model

import com.google.firebase.database.Exclude

data class Received(
    @get:Exclude
    var id: String? = "",

    var receiversId: String? ="",
    var sendersId: String? = "",
    var amount: String? = "",
    var time: String? = ""
) {
}