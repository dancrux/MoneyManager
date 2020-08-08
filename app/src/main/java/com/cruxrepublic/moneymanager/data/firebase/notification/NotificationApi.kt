package com.cruxrepublic.moneymanager.data.firebase.notification

import com.cruxrepublic.moneymanager.data.firebase.notification.Constants.Companion.CONTENT_TYPE
import com.cruxrepublic.moneymanager.data.firebase.notification.Constants.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {
    @Headers("Authorization: key =$SERVER_KEY", "Content-type: $CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotifications(
        @Body notificationData: PushNotificationData
    ): Response<ResponseBody>
}