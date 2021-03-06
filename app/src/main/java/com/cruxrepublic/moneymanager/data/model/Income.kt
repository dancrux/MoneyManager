package com.cruxrepublic.moneymanager.data.model

import com.google.firebase.database.Exclude

/**
 * Data class that captures user information for logged in users retrieved from UserRepository
 */
data class Income(
    var sourceOfIncome: String? = "",
    var amount: String? = "",
    var time: String? = "",

    @get:Exclude
    var id: String? = ""
)