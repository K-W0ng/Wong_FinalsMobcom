package edu.mobcom.wong_finalsmobcom


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
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
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val pass = binding.etPasswordLogin.text.toString()

            //email and password validation
            if (checkEmpty(email, pass)) {
                Toast.makeText(this, "Email and Password is required", Toast.LENGTH_SHORT).show()
            } else {
                //authenticate
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Welcome $email", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, HomeActivity::class.java)
                        //pass data
                        with(i) {
                            val recEmail: EditText = findViewById(R.id.etEmailLogin)
                            putExtra("Email", recEmail.text.toString())
                        }
                        startActivity(i)
                    } else {
                        Toast.makeText(this, "Please use an existing account or register a new account", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        binding.btnSignUpLogin.setOnClickListener {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        }
    }

    //validation function for empty string
    private fun checkEmpty(email: String, pass: String): Boolean {
        return email.isBlank() || pass.isBlank()
    }

}
