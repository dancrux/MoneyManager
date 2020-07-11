package com.cruxrepublic.moneymanager.data.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class Preferences (context: Context){
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
   get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun firstTimeLoginBoolean(key: String): Boolean{
      return preference.getBoolean(key, false)
    }
    fun saveFirstTimeLoginBoolean(key:String, savedAt: Boolean?) {
        preference.edit().putBoolean(
            key,
            savedAt!!
        ).apply()
    }
}