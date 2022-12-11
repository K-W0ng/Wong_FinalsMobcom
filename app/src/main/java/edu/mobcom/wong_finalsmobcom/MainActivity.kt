package edu.mobcom.wong_finalsmobcom

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import edu.mobcom.wong_finalsmobcom.databinding.ActivityLoginBinding
import edu.mobcom.wong_finalsmobcom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProceed.setOnClickListener {
            val  i = Intent (this, LoginActivity::class.java)
            startActivity(i)
        }
    }


}