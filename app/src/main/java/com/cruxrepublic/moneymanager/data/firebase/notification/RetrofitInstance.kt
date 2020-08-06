package com.cruxrepublic.moneymanager.data.firebase.notification

import com.cruxrepublic.moneymanager.data.firebase.notification.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val retrofitApi by lazy {
            retrofit.create(NotificationApi::class.java)
        }
    }
}