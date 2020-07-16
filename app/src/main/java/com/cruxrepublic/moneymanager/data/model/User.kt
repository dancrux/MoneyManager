package com.cruxrepublic.moneymanager.data.model

import com.google.firebase.database.Exclude

data class User(
    var  firstName: String? = "",
    var surname: String? = "",
    var id: String? = "",
    var email: String? = "",
    var country: String? = "",
    var age: String? = "",
    var  phoneNumber: String? = "",
    var sex: String? = ""
)  {
//    @Exclude
//    fun toMap(): Map<String, String?>{
//        return  mapOf(
//            "firstName" to firstName,
//            "surname" to surname,
//            "id" to id,
//            "email" to email,
//            "country" to country,
//            "age" to age,
//            "phoneNumber" to phoneNumber,
//            "sex" to sex
//        )
//    }
}