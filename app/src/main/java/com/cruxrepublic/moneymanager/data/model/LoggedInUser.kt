package com.cruxrepublic.moneymanager.data.model

/**
 * Data class that captures user information for logged in users retrieved from UserRepository
 */
data class LoggedInUser(
    val userId: String,
    val accountId: String
)