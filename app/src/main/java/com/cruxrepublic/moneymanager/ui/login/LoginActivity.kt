package com.cruxrepublic.moneymanager.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        notRegisteredText.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}