package edu.mobcom.wong_finalsmobcom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import edu.mobcom.wong_finalsmobcom.databinding.ActivityLoginBinding
import edu.mobcom.wong_finalsmobcom.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor=sharedPref.edit()

        binding.btnRegisterRegister.setOnClickListener {
            val email = binding.etEmailRegister.text.toString()
            val phone = binding.etPhoneRegister.text.toString()
            val firstName = binding.etFirstRegister.text.toString()
            val lastName = binding.etLastRegister.text.toString()
            val pass1 = binding.editTextTextPassword.text.toString()
            val pass2 = binding.editTextTextPassword2.text.toString()

            if (checkEmpty(email,phone,firstName,lastName,pass1,pass2)){
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show()
            }else if (confirmPwd(pass1, pass2)){

            }
                auth.createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(this){
                    //Save data to Shared Pref
                    if (it.isSuccessful){
                        editor.apply{
                            putString("email", email)
                            putString("phone", phone)
                            apply()
                        }
                        Toast.makeText(this, "You are registered!", Toast.LENGTH_SHORT).show()
                        val i= Intent(this, LoginActivity::class.java)
                        startActivity(i)
                        finish()
                    }else{
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
    private fun checkEmpty(email: String, phone: String, pass1: String, pass2: String, firstName:String, lastName:String):Boolean {
        return email.isEmpty() || phone.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
    }

    private fun confirmPwd(pass1: String, pass2: String):Boolean{
        return pass1 == pass2
    }
}