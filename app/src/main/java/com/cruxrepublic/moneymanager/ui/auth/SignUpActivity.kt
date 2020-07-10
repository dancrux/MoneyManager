package com.cruxrepublic.moneymanager.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.SignUpActivityBinding
import com.cruxrepublic.moneymanager.ui.utils.startMainActivity
import com.cruxrepublic.moneymanager.ui.utils.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sign_up_activity.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class SignUpActivity() : AppCompatActivity(), KodeinAware, AdapterView.OnItemSelectedListener,
    AuthListener {

    private lateinit var binding: SignUpActivityBinding
    private lateinit var authViewModel: AuthViewModel
    override val kodein by kodein()
    private val factory by instance<AuthViewModelFactory>()
    private var mAuth: FirebaseAuth? = null

    var sex: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.sign_up_activity)

//        val firebaseDataSource= FireBaseDataSource()
//        val repository = UserRepository(firebaseDataSource)
//        val factory = AuthViewModelFactory(repository, this)
        authViewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.authViewModel = authViewModel
        authViewModel.authListener = this

        ArrayAdapter.createFromResource(
            this, R.array.gender_spinner,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.genderSpinner.adapter = arrayAdapter
            genderSpinner.onItemSelectedListener = this
        }
    }


    override fun validateFields(): Boolean {
        binding.emailEdit.text.trim()
        binding.passwordEdit.text.trim()
        if (binding.firstNameEdit.text.isNullOrEmpty()) {
            binding.firstNameEdit.error = "Enter First Name"
            binding.firstNameEdit.requestFocus()
            return false
        }
        if (binding.surnameEdit.text.isNullOrEmpty()) {
            binding.surnameEdit.error = "Enter Surname Name"
            binding.surnameEdit.requestFocus()
            return false
        }
        if (binding.emailEdit.text.isNullOrEmpty()) {
            binding.emailEdit.error = "Email is Required"
            binding.emailEdit.requestFocus()
            return false

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEdit.text).matches()) {
            binding.emailEdit.error = "Invalid Email"
            binding.emailEdit.requestFocus()
            return false
        }
        if (binding.phoneEdit.text.isNullOrEmpty() || binding.phoneEdit.text.length < 10) {
            binding.phoneEdit.error = "Invalid Phone Number"
            binding.phoneEdit.requestFocus()
            return false
        }
        if (binding.ageEdit.text.isNullOrEmpty()) {
            binding.ageEdit.error = "Enter Your Age"
            binding.ageEdit.requestFocus()
            return false
        }
        if (binding.countryEdit.text.isNullOrEmpty()) {
            binding.countryEdit.error = "Enter Your Country"
            binding.countryEdit.requestFocus()
            return false
        }
        if (binding.passwordEdit.text.isNullOrEmpty() || binding.passwordEdit.text.length < 6) {
            binding.passwordEdit.error = "Invalid Password"
            binding.passwordEdit.requestFocus()
            return false
        }
        if (genderSpinner.selectedItem.toString() == "Gender") {
            toast("Invalid Gender Selection")
           genderSpinner.requestFocus()
            return false
        }
        return true

    }

    override fun onStarted() {
        progressBarIn.visibility = View.VISIBLE
    }


    override fun onSuccess() {
        progressBarIn.visibility = View.GONE
        startMainActivity()
    }

    override fun notSuccessful() {
        progressBarIn.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        toast(message)
    }



    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent!!.id) {
            R.id.genderSpinner -> if (!parent.selectedItem.toString().equals(
                    "Gender",
                    ignoreCase = true
                )
            ) {
                authViewModel.sex = parent.selectedItem.toString()
            }
        }
    }

}