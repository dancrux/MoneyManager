package com.cruxrepublic.moneymanager.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.ActivitySignUpBinding
import com.cruxrepublic.moneymanager.ui.utils.startMainActivity
import com.cruxrepublic.moneymanager.ui.utils.toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(),
    AuthListener, KodeinAware {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var authViewModel: AuthViewModel
    override val kodein by kodein()
    private val factory by instance<AuthViewModelFactory>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        authViewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.authViewModel = authViewModel

        authViewModel.authListener = this

    }

    override fun validateFields(){
            binding.emailEdit.text.trim()
           binding.passwordEdit.text.trim()
            if (binding.firstNameEdit.text.isNullOrEmpty()){
                binding.firstNameEdit.error = "Enter First Name"
                binding.firstNameEdit.requestFocus()
                return
            }
            if ( binding.surnameEdit.text.isNullOrEmpty()){
                binding.surnameEdit.error = "Enter Surname Name"
                binding.surnameEdit.requestFocus()
                return
            }
        if (binding.emailEdit.text.isNullOrEmpty()){
                binding.emailEdit.error = "Email is Required"
                binding.emailEdit.requestFocus()
                return

            }
        if ( !Patterns.EMAIL_ADDRESS.matcher( binding.emailEdit.text).matches()){
                binding.emailEdit.error = "Enter First Name"
                binding.emailEdit.requestFocus()
                return
            }
        if (binding.phoneEdit.text.isNullOrEmpty() ||  binding.phoneEdit.text.length < 10){
                binding.phoneEdit.error = "Enter First Name"
                binding.phoneEdit.requestFocus()
                return
            }
        if ( binding.ageEdit.text.isNullOrEmpty() ){
                binding.ageEdit.error = "Enter First Name"
                binding.ageEdit.requestFocus()
                return
            }
        if (binding.countryEdit.text.isNullOrEmpty() ){
                binding.countryEdit.error = "Enter First Name"
                binding.countryEdit.requestFocus()
                return
            }
        if (binding.passwordEdit.text.isNullOrEmpty() ||  binding.passwordEdit.text.length < 6){
                binding.passwordEdit.error = "Enter First Name"
                binding.passwordEdit.requestFocus()
                return
            }

        }

    override fun onStarted() {
        progressBarIn.visibility = View.VISIBLE
    }


    override fun onSuccess() {
        progressBarIn.visibility = View.GONE
       startMainActivity()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Welcome")
        builder.setMessage("Add Your First Income")
        builder.setCancelable(true)

        builder.setPositiveButton("Add"){
            dialog, which ->  showAddIncome()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onFailure(message: String) {
        toast(message)
    }
    private fun showAddIncome(){
        val view = layoutInflater.inflate(R.layout.fragment_add_income_dialog, null)
            val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()

    }
}