package com.cruxrepublic.moneymanager.ui.auth

import android.widget.EditText

interface AuthListener {
    fun validateFields()
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}