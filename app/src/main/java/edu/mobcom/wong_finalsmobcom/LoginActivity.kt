package edu.mobcom.wong_finalsmobcom

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private  lateinit var btnSignUpLogin: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignUpLogin = findViewById(R.id.btnSignUpLogin)

        fun openRegisterActivity(){
            val iRegistrationActivity = Intent(this, RegistrationActivity::class.java)
            startActivity(iRegistrationActivity)
        }

        btnSignUpLogin.setOnClickListener {
            openRegisterActivity()
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}