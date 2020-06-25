package com.cruxrepublic.moneymanager.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.ActivityLoginBinding
import com.cruxrepublic.moneymanager.ui.utils.startMainActivity
import com.cruxrepublic.moneymanager.ui.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity() : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory by instance<AuthViewModelFactory>()
    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        authViewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.authViewModel = authViewModel
        authViewModel.authListener = this

    }

    override fun validateFields() {
        binding.emailEditText.text.trim()
        binding.passwordEditText.text.trim()
        if (binding.emailEditText.text.isNullOrEmpty()){
            binding.emailEditText.error = "Email is Required"
            binding.emailEditText.requestFocus()
            return

        }
        if ( !Patterns.EMAIL_ADDRESS.matcher( binding.emailEditText.text).matches()){
            binding.emailEditText.error = "Enter First Name"
            binding.emailEditText.requestFocus()
            return
        }
        if (binding.passwordEditText.text.isNullOrEmpty() ||  binding.passwordEditText.text.length < 6){
            binding.passwordEditText.error = "Enter First Name"
            binding.passwordEditText.requestFocus()
            return
        }
    }

    override fun onStarted() {
        progressBarIn.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressBarIn.visibility = View.GONE
        startMainActivity()
    }

    override fun onFailure(message: String) {
        toast(message)
    }
    override fun onStart() {
        super.onStart()
        authViewModel.user?.let {
            startMainActivity()
        }
    }
}