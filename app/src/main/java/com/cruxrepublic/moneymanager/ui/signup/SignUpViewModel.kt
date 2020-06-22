package com.cruxrepublic.moneymanager.ui.signup

import android.app.PendingIntent.getActivity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpViewModel: ViewModel() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var email : String = ""
    var password: String =""
    var firstName: String =""
    var surname: String = ""
    var country: String = ""
    var age: String =""
    var phoneNumber = ""
    var sex =""


    fun onSignUpButtonClick(view: View){

    }
    fun registerUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(){task ->
                if (task.isSuccessful){
                    val user = User(
                        email,firstName, surname, country, age, phoneNumber,sex
                    )
                    FirebaseAuth.getInstance().currentUser?.uid?.let {
                        FirebaseDatabase.getInstance().getReference("Users")
                            .child(it).setValue(user).addOnCompleteListener {
                                if (task.isSuccessful){
//                                    i need to open the home screen here
                                }
                            }
                    }

            }else {
//               tryig to show a toast here is sign Up fails
            }
        }

    }

}