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
import edu.mobcom.wong_finalsmobcom.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var username: TextView = findViewById(R.id.tvNameProfile)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val pass = binding.etPasswordLogin.text.toString()

            if (checkEmpty(email, pass)) {
                Toast.makeText(this, "Email and Password is required", Toast.LENGTH_SHORT).show()
            } else {
                //authenticate
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {

                    if (it.isSuccessful) {
                        Toast.makeText(this, "Welcome $email", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, HomeActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(
                            this,
                            "Please use an existing account or register a new account",
                            Toast.LENGTH_LONG
                        ).show()
                        val i = Intent(this, LoginActivity::class.java)
                        startActivity(i)
                    }
                }
            }
        }
        binding.btnSignUpLogin.setOnClickListener {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        }
    }

    private fun checkEmpty(email: String, pass: String): Boolean {
        return email.isEmpty() || pass.isEmpty()
    }

}
