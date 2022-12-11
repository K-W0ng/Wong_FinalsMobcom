package edu.mobcom.wong_finalsmobcom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.mobcom.wong_finalsmobcom.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.backProfile.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

        binding.btnSaveProfile.setOnClickListener {
            val email = binding.tvEmailProfile.text.toString()
            val phone = binding.tvNumberProfile.text.toString()

            editor.apply {
                putString("email", email)
                putString("phone", phone)
                apply()
            }
            Toast.makeText(this, "Successfully Updated!", Toast.LENGTH_SHORT).show()

        }

        binding.btnView.setOnClickListener {
            binding.tvEmailProfile.setText("")
            binding.tvNumberProfile.setText("")
            binding.tvEmailProfile.setText(sharedPref.getString("email", ""))
            binding.tvNumberProfile.setText(sharedPref.getString("phone", ""))
        }

        binding.btnLogoutProfile.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}