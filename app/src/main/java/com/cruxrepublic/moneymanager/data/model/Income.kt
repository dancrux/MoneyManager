package com.cruxrepublic.moneymanager.data.model

/**
 * Data class that captures user information for logged in users retrieved from UserRepository
 */
data class Income(
    val sourceOfIncome: String,
    val amount: String,
    var time: String
)