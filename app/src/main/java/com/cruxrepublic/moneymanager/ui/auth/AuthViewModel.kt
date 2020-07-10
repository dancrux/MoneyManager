package com.cruxrepublic.moneymanager.ui.auth

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.cruxrepublic.moneymanager.data.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class AuthViewModel(private val repo: UserRepository): ViewModel() {

//    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var email : String = ""
    var password: String = ""
    var firstName: String = ""
    var surname:  String = ""
    var country: String = ""
    var age: String = ""
    var phoneNumber = ""
    var sex = ""

    lateinit var authListener: AuthListener
    private var repository = repo
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val disposables = CompositeDisposable()


    val user by lazy {
        repository.currentUser()
    }


//    fun onSignUpButtonClick(view: View) {
//        if (authListener.validateFields()){
//            authListener.onStarted()
//        }else return
//        uiScope.launch {
//            try {
//                val authResponse = repository.register(
//                    email,
//                    password,
//                    firstName,
//                    surname,
//                    country,
//                    age,
//                    phoneNumber,
//                    sex)
//                authResponse.let {  authListener.onSuccess() }
//
//            }catch (e: ApiException){
//                authListener.onFailure(e.message!!)
//            }
//
//            }
//
//
//    }

    fun onItemSelected(
        parent: AdapterView<*>,
        view: View?,
        position: Int,
        id: Long
    ) {
        val text = parent.getItemAtPosition(position).toString()
        Toast.makeText(parent.context, text, Toast.LENGTH_SHORT).show()
    }
    fun onSignUpButtonClick(view: View) {
    if (authListener.validateFields()) {
            authListener.onStarted()
        } else return
        val disposable = repository.register(
            email,
            password,
            firstName!!,
            surname!!,
            country,
            age,
            phoneNumber,
            sex
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener.onSuccess()
            }, {
                authListener.notSuccessful()
                authListener.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }


//    fun signin(view: View) {
//        if (authListener.validateFields()) {
//            authListener.onStarted()
//        } else return
//        uiScope.launch {
//            try {
//                val authResponse = repository.login(email, password)
//                authResponse.let { authListener.onSuccess() }
//
//            } catch (e: ApiException) {
//                authListener.onFailure(e.message!!)
//            }
//
//        }
//    }

    fun signin(view: View) {
        if (authListener.validateFields()) {
            authListener.onStarted()
        } else return
        val disposable = repository.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener.onSuccess()
            }, {
                authListener.notSuccessful()
                authListener.onFailure(it.message!!)
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

    }

