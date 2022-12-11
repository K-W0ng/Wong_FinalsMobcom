package edu.mobcom.wong_finalsmobcom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        //receive data on login
//        val tvNameProfile = findViewById<TextView>(R.id.tvNameProfile)
//        val rCE=intent!!.getStringExtra("sendFname").toString()
//        val rec: String = rCE
//
//        tvNameProfile.setText(rec)

        //back button
        binding.backProfile.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

        //save details
        binding.btnSaveProfile.setOnClickListener {

            val email = binding.etEmailProfile.text.toString()
            val phone = binding.etNumberProfile.text.toString()
            val fname = binding.etFNProfile.text.toString()
            val lname = binding.etLNProfile.text.toString()

            editor.apply {
                putString("email", email)
                putString("phone", phone)
                putString("fname", fname)
                putString("lname", lname)
                apply()
            }

            Toast.makeText(this, "Successfully Updated!", Toast.LENGTH_SHORT).show()

        }

        //view details
        binding.btnView.setOnClickListener {
            binding.etEmailProfile.setText("")
            binding.etNumberProfile.setText("")
            binding.etFNProfile.setText("")
            binding.etLNProfile.setText("")
            binding.etEmailProfile.setText(sharedPref.getString("email", ""))
            binding.etNumberProfile.setText(sharedPref.getString("phone", ""))
            binding.etFNProfile.setText(sharedPref.getString("fname", ""))
            binding.etLNProfile.setText(sharedPref.getString("lname", ""))

        }

        //logout
        binding.btnLogoutProfile.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}