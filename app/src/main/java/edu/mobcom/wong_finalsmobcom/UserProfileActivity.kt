package edu.mobcom.wong_finalsmobcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding : UserProfileActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationActivity.inflate
        setContentView(R.layout.activity_user_profile)
    }
}