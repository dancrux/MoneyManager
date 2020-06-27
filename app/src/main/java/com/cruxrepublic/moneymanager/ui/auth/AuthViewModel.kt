package com.cruxrepublic.moneymanager.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val repository: UserRepository) : ViewModel() {


    //    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var email: String? = null
    var password: String? = null
    var firstName: String = ""
    var surname: String = ""
    var country: String = ""
    var age: String = ""
    var phoneNumber = ""
    var sex = ""
    var errorMessage = ""

    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    fun onSignUpButtonClick(view: View) {
        authListener?.validateFields()
        authListener?.onStarted()
        val disposable = repository.register(
            email!!,
            password!!,
            firstName,
            surname,
            country,
            age,
            phoneNumber,
            sex
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun signin(view: View) {
        authListener?.validateFields()
        authListener?.onStarted()
        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun onSignUpTextClicked(view: View) {
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }

    }

    fun onSignInTextClicked(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
//    private fun registerUser(email: String, password: String){
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(){task ->
//                if (task.isSuccessful){
//                    val user = User(
//                        email, firstName, surname, country, age, phoneNumber, sex
//                    )
//
//                    FirebaseAuth.getInstance().currentUser?.uid?.let {
//                        FirebaseDatabase.getInstance().getReference("Users")
//                            .child(it).setValue(user).addOnCompleteListener {
//                                if (task.isSuccessful){
//                                    authListener?.onSuccess()
//                                }
//                            }
//                    }
//
//                }else {
//                    errorMessage = task.exception?.message.toString()
//                    authListener?.onFailure(errorMessage)
//                }
//            }
//
//    }


}