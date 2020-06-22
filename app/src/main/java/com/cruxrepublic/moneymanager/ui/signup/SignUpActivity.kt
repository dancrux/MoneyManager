package com.cruxrepublic.moneymanager.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.ActivitySignUpBinding
import com.cruxrepublic.moneymanager.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel


        signInButton.setOnClickListener {
          validateFields()
            signUpViewModel.registerUser(signUpViewModel.email,signUpViewModel.password)

        }

        signinHereText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun validateFields(){
        val email = emailEdit.text.toString().trim()
        val password = passwordEdit.toString().trim()

        if (binding.firstNameEdit.text.isEmpty()){
            binding.firstNameEdit.error = "Enter First Name"
            firstNameEdit.requestFocus()
            return
        }
        if (binding.surnameEdit.text.isEmpty()){
            surnameEdit.error = "Enter Surname"
            surnameEdit.requestFocus()
            return
        }

        if (email.isEmpty()){
            emailEdit.error = "Email Required"
            emailEdit.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdit.error = "Valid Email Required"
            emailEdit.requestFocus()
            return
        }
        if (binding.phoneEdit.text.isEmpty() || password.length < 6){
            phoneEdit.error = "Enter Phone Number"
            phoneEdit.requestFocus()
            return
        }
        if (binding.ageEdit.text.isEmpty() || password.length < 6){
            ageEdit.error = "Enter your age"
            ageEdit.requestFocus()
            return
        }
        if (binding.countryEdit.text.isEmpty() || password.length < 6){
            countryEdit.error = "Password should not be less than 6 characters"
            countryEdit.requestFocus()
            return
        }
        if (password.isEmpty() || password.length < 6){
            passwordEdit.error = "Password should not be less than 6 characters"
            passwordEdit.requestFocus()
            return
        }


    }

}